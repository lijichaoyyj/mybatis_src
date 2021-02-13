package com.cst.apply;

import com.cst.bean.User;
import com.google.common.collect.Maps;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

interface  UserMapper{
    @Select("SELECT * FROM blog WHERE id = #{arg0} and name=#{arg1}")
    List<User> selectUserList(Integer id, String name);

}
public class MnApplication {

    public static void main(String[] args) {
        UserMapper  userMapper= (UserMapper) Proxy.newProxyInstance(MnApplication.class.getClassLoader(),
                new Class<?>[]{UserMapper.class}, new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        String methodName = method.getName();
                        System.out.println("methodName:"+methodName);
                        Select selectAnn = method.getAnnotation(Select.class);
                        Map<String, Object> argNameMap = buildMethodArgNameMap(method, args);

                        if(selectAnn!=null){
                            String[] sqlArr = selectAnn.value();
                            //给sql注入值.
                            String sql = parseSQL(sqlArr[0], argNameMap);

                            System.out.println("sqlArr:"+Arrays.toString(sqlArr));
                            System.out.println("sql:"+sql);
                        }

                        return null;
                    }
                });
        userMapper.selectUserList(1,"testNameVal");
    }

    /**
     * 解析sql
     * @param sql
     * @param nameArgMap
     * @return
     */
    public  static String parseSQL(String sql,Map<String,Object> nameArgMap){

     String parseSQL="";
        StringBuilder stringBuilder = new StringBuilder();
        int length = sql.length();
        for (int i=0;i<length;i++)
        {
            char c=sql.charAt(i);
            if(c=='#'){
                int nextIndex=i+1;
                char nextChar=sql.charAt(nextIndex);
                if(nextChar=='{'){
                    StringBuilder argSB = new StringBuilder();
                     i = parseSqlArg(argSB, sql, nextIndex);
                    String val = nameArgMap.get(argSB.toString()).toString();
                    stringBuilder.append("'"+val+"'");
                }
            }else{
                stringBuilder.append(c);
            }

        }

   return stringBuilder.toString();
    }

    private static int parseSqlArg(StringBuilder argSB,String sql,int nextIndex) {
     //SELECT * FROM blog WHERE id = #{id}
        char[] chars = sql.toCharArray();
        for(int i=nextIndex+1;i<sql.length();i++){

            if(chars[i]!='}'){
                argSB.append(chars[i]);
            }else if(chars[i]=='}'){
                argSB.toString();
                nextIndex=i;
                break;
            }

        }
        return nextIndex ;

    }


    public static Map<String,Object> buildMethodArgNameMap(Method method, Object[]args) {
        Objects.requireNonNull(method);

        Parameter[] parameters = method.getParameters(); //获取方法的参数.
        //HashMap<String, Object> nameArgMap = Maps.newHashMap();
//        Arrays.asList(parameters).forEach(parameter -> {
//            String name = parameter.getName();
//            nameArgMap.put(name,args[index[0]]);
//            index[0]++;
//
//        });
        //数据量特别大时使用.
        Map<String, Object> concurrentMap = Maps.newConcurrentMap();

          final int  index[]={0};
        //parallel:/ˈpærəlel/:平行线 平行 并行
         Arrays.asList(parameters).parallelStream().forEach(parameter -> {
            String name = parameter.getName();
            concurrentMap.put(name,args[index[0]]);
            index[0]++;

        });

//        for (int i=0;i<parameters.length;i++) {
//            String parameterName = parameters[i].getName();
//            System.out.println("parameterName = " + parameterName);
//            nameArgMap.put(parameterName,args[i]);
//        }


        return  concurrentMap;
    }


}

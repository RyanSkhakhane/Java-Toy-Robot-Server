echo  " "
echo ">>>>>>>>>>>>>>>>>>>>  C_O_M_P_I_L_I_N_G :) <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
mvn compile

kill -9 $(lsof -t -i tcp:5000)
echo " "
echo ">>>>>>>>>>>>>>>>>>>>>>>>> R_U_N_N_I_N_G    R_E_F_E_R_E_N_C_E   S_E_R_V_E_R      <<<<<<<<<<<<<<<<<<<<<<<<"
echo ">>>>>>>>>>>>>>>>>>>>>>>>>                    _&_                                <<<<<<<<<<<<<<<<<<<<<<<<"
echo ">>>>>>>>>>>>>>>>>>>>>>>>>                T_E_S_T_I_N_G                          <<<<<<<<<<<<<<<<<<<<<<<<"
echo  " "
java -jar ./src/libs/reference-server-0.2.3.jar & mvn test
kill -9 $(lsof -t -i tcp:5000)

#
#echo " "
#echo ">>>>>>>>>>>>>>>>>>>>>>>>>  R_U_N_N_I_N_G    O_U_R   S_E_R_V_E_R      <<<<<<<<<<<<<<<<<<<<<<<<"
#echo ">>>>>>>>>>>>>>>>>>>>>>>>>                    _&_                                <<<<<<<<<<<<<<<<<<<<<<<<"
#echo ">>>>>>>>>>>>>>>>>>>>>>>>>                T_E_S_T_I_N_G                          <<<<<<<<<<<<<<<<<<<<<<<<"
#echo  " "
#java -jar ./target/Robot_Server-1.0-SNAPSHOT-jar-with-dependencies.jar & mvn test
#kill -9 $(lsof -t -i tcp:5000)

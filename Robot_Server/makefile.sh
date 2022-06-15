echo  " "
echo ">>>>>>>>>>>>>>>>>>>>  C O M P I L I N G :) <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
mvn compile


echo " "
echo ">>>>>>>>>>>>>>>>>>>>>>>>> V E R I F Y I N G :) <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
mvn verify

echo " "
echo ">>>>>>>>>>>>>>>>>>>>>>>>> R U N N I N G    R E F E R E N C E   S E R V E R :) <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
#java -jar ./src/libs/reference-server-0.1.0.jar



#echo " "
#echo ">>>>>>>>>>>>>>>>>>>>>>>>>  T E S T I N G :) <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"
#mvn test
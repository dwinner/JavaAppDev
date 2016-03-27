wsimport -keep -p com.horstmann.corejava.server http://localhost:8080/WebServices/warehouse?wsdl
pause
javac WarehouseClient.java
java WarehouseClient
pause
javac ./com/horstmann/corejava/Warehouse.java
javac ./com/horstmann/corejava/WarehouseServer.java
wsgen -classpath . com.horstmann.corejava.Warehouse
java com.horstmann.corejava.WarehouseServer
pause
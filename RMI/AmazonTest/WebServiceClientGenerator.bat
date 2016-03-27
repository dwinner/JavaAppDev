wsimport -p com.horstmann.amazon http://webservices.amazon.com/AWSECommerceService/AWSECommerceService.wsdl
pause
jar cvf AmazonWs.jar com/horstmann/amazon/*.class
pause
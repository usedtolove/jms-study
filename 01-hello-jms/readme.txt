1.setup jdk 1.6 and maven 3.x

2.import project into IDE(IDEA).

3.download ActiveMQ
at: http://activemq.apache.org/index.html
and setup.

4.startup ActiveMQ
apache-activemq-5.8.0/bin/activemq.bat
and login control panel
http://localhost:8161/admin/
username:admin
password:admin
configure this in the conf/jetty-real.properties file

5.run jsm unit test
5.1   Queue Text Message
5.1.1 run TestTextMessage.testProducer()
5.1.2 check info in activemq control panel
5.1.3 TestTextMessage.testConsumer()
5.1.4 check info in activemq control panel again

5.2   Queue Object Message
5.2.1 TestObjectMessage.testProducer()
5.2.1 check info in activemq control panel
5.2.1 TestObjectMessage.testConsumer()
5.2.1 check info in activemq control panel again

5.3  Topic Text Message
5.3.1 run testConsumer() several times, in my case is 3
5.3.2 run testProducer() one time
5.3.3 check info at both window(console) and have fun!

6.test web queue use case
6.1 cd 01-hello/web 
    mvn jetty:run
6.2 init h2 table
    run com.test.init.CreateH2Table.init()
6.3 go to http://localhost:8889/jsp/register.jsp 
    type some test chars and submit several times;
6.4 check log at console.
    check h2 database ,

7. test web topic use case
7.1   add web socket
   follow:http://activemq.apache.org/websockets.html
7.2   restart ActiveMQ
7.3   cd 01-hello/web
      mvn jetty:run
7.4   go to http://localhost:8889/jsp/subscriber-list.jsp
      have fun!

==============================================
Thanks to :
http://jmesnil.net/stomp-websocket/doc/

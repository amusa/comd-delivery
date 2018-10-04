FROM java:8
MAINTAINER mayemij@gmail.com
EXPOSE 8090
COPY target/comd-delivery-api-thorntail.jar /maven/
COPY lib/libsapjco3.so /maven/lib/

CMD java -Djava.net.preferIPv4Stack=true  -Djava.library.path=/maven/lib/ -jar /maven/comd-creditnote-api-thorntail.jar
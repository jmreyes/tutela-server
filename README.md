## Tutela Server

Final Project for the "Mobile Cloud Computing with Android" Specialization on Coursera.

# Running the Application

Please note, the keystore for HTTPS is not included. The MongoDB connection must be configured in src/main/resources.

To run the application:

1. (Menu Bar) Run->Run Configurations
2. Under Java Applications, select your run configuration for this app
3. Open the Arguments tab
4. In VM Arguments, provide the following information to use the
   default keystore provided with the sample code:

   -Dkeystore.file=src/main/resources/private/keystore -Dkeystore.pass=changeit

5. Note, this keystores is highly insecure! If you want more security, you 
   should obtain a real SSL certificate:

   http://tomcat.apache.org/tomcat-7.0-doc/ssl-howto.html
   
6. This keystore is not secured and should be in a more secure directory -- preferably
   completely outside of the app for non-test applications -- and with strict permissions
   on which user accounts can access it

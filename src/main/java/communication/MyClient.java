package communication;

import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.security.KeyStore;
import java.math.BigInteger;

public class MyClient {
  private String host;
  private int port;
  private SSLSocket socket;
  private PrintWriter sender;
  private BufferedReader receiver;

  public MyClient (String host, int port) {
    this.host = host;
    this.port = port;
    try {
      setup();
    } catch(Exception e) {
      System.out.println("Unable to initiate client.");
      e.printStackTrace();
    }
  }

  public void setup() throws Exception {
    KeyStore ks = KeyStore.getInstance("JKS");
    KeyStore ts = KeyStore.getInstance("JKS");
    KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
    TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
    SSLContext context = SSLContext.getInstance("TLS");

    char[] password = authenticateUser(ks, ts);

    kmf.init(ks, password);
    tmf.init(ts);
    context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
    SSLSocketFactory factory = context.getSocketFactory();

    socket = (SSLSocket)factory.createSocket(host, port);
    System.out.println("\nSocket before handshake: \n" + socket + "\n");

    String subject = "";

    socket.startHandshake();

    SSLSession session = socket.getSession();
    X509Certificate cert = (X509Certificate)session.getPeerCertificates()[0];
    subject = cert.getSubjectDN().getName();
    String issuer = cert.getIssuerDN().getName();
    BigInteger serialNum = cert.getSerialNumber();
    System.out.println("certificate name (subject DN field) on certificate received from communication.server:\n" + subject + "\n");
    // System.out.println("communication.server name (cert issuer DN field): " + issuer);
    // System.out.println("communication.server, x509 certificate serial number: " + serialNum);
    System.out.println("socket after handshake:\n" + socket + "\n");
    System.out.println("secure connection established\n\n");
    // System.out.println("Server is awaiting your message.");

    sender = new PrintWriter(socket.getOutputStream(), true);
    receiver = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    System.out.println(receiver.readLine());
  }

  public String send(String msg) {
    sender.println(msg);
    String result = "";
    try {
      result = receiver.readLine();
    } catch(Exception e) {
      System.out.println("Unable to get response from server.");
    }
    return result;
  }

  private static char[] authenticateUser(KeyStore keyStore, KeyStore trustStore) {
    boolean userAuthenticated = false;
    char[] password = null;
    while (!userAuthenticated) {

      password = readPassword();

      try {
        keyStore.load(new FileInputStream("./scripts/certs/clientkeystore"), password);
        trustStore.load(new FileInputStream("./scripts/certs/clienttruststore"), password);
        userAuthenticated = true;
      } catch(Exception e) {
        System.out.println("Invalid password, try again: ");
        e.printStackTrace();
      }
    }
    return password;
  }

  private static char[] readPassword() {
    System.out.println("Enter keystore password: ");
    return System.console().readPassword();
  }

}

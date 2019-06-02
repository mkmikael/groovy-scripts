@Grab(group = 'com.sun.mail', module = 'javax.mail', version = '1.6.2')
import javax.mail.PasswordAuthentication
import javax.mail.Authenticator
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

def email = "example@gmail.com",
    password = "password",
    to = "to@gmail.com",
    subject = "Andamento do Projeto",
    text = """
     <html>
     <body>
         <h1>Andamento do Projeto</h1>
         <img src="https://cdn1.imggmi.com/uploads/2019/6/2/34d7d86a76c3676f6101b466d4902a3e-full.png" height="300" width="800" />
     </body>
     </html>
    """.toString()
def props = new Properties()
props.put("mail.smtp.host", "smtp.gmail.com")
props.put("mail.smtp.auth", "true")
props.put("mail.smtp.socketFactory.port", "465")
props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
props.put("mail.smtp.socketFactory.fallback", "false")

def auth = new Authenticator() {
    PasswordAuthentication getPasswordAuthentication() {
        new PasswordAuthentication(email, password);
    }
}
def session = Session.getInstance(props, auth)

def msg = new MimeMessage(session)
msg.setContent(text, "text/html");
msg.setSubject(subject)
msg.setFrom(new InternetAddress(email))
msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to))

Transport.send(msg)

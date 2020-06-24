/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chat;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.logging.Logger;
import java.util.logging.Level;
//import javax.swing.JList;
import javax.swing.JOptionPane;
import org.omg.CORBA.portable.UnknownException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


//Encryption ee;
////    ee = new Encryption(); 


public class Chat_Window extends javax.swing.JFrame implements Runnable{

    Socket client;
    ServerSocket server;
    BufferedReader Ser_Read, Cli_Read;
    BufferedWriter Ser_Write,Cli_Write;
    private static SecretKeySpec secretKey;
    private static byte[] key;

       public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }  
    
    
    /**
     * Creates new form Chat_Window
     */
    public Chat_Window() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. T
     * he content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cbUser = new javax.swing.JComboBox<>();
        txtUserName = new javax.swing.JTextField();
        btnConnection = new javax.swing.JButton();
        txtMsgText = new javax.swing.JTextField();
        msgSend = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Lchat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Poor Richard", 1, 18)); // NOI18N
        jLabel2.setText("Socket Lovers");

        jButton1.setText("About");

        cbUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cbUser.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SERVER", "CLIENT" }));
        cbUser.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbUserItemStateChanged(evt);
            }
        });

        txtUserName.setText("Username");

        btnConnection.setText("ON");
        btnConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectionActionPerformed(evt);
            }
        });

        txtMsgText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMsgTextActionPerformed(evt);
            }
        });

        msgSend.setText("Send");
        msgSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgSendActionPerformed(evt);
            }
        });

        Lchat.setColumns(20);
        Lchat.setRows(5);
        jScrollPane2.setViewportView(Lchat);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbUser, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtMsgText, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(msgSend))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnConnection)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(cbUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnection))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMsgText, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(msgSend))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void btnConnectionActionPerformed(java.awt.event.ActionEvent evt) {                                              
       if(btnConnection.getText().equals("CONNECT")){
           btnConnection.setText("DISCONNECT");
           client_con();
           Thread thread = new Thread((Runnable) this);
           thread.start();
       } else if(cbUser.getSelectedItem().equals("SERVER")) {
           btnConnection.setText("OFF");
           read_con();
           Thread thread = new Thread((Runnable) this);
           thread.start();
       }
    }                                             

    
    private void msgSendActionPerformed(java.awt.event.ActionEvent evt) {                                        
        
        final String secretKey = "ssshhhhhhhhhhh!!!!";
        String encryptedString = encrypt(txtMsgText.getText(),secretKey) ;
        String decryptedString = decrypt(encryptedString, secretKey) ;

        
        try{
            Ser_Write.write(txtUserName.getText() +":" +decryptedString);
            Ser_Write.newLine();
            Ser_Write.flush();
            
        } catch (IOException ex) {
         Logger.getLogger(Chat_Window.class.getName()).log(Level.SEVERE,null, ex);
        }
             
        Lchat.append("\n"+"Me : " + decryptedString);
        txtMsgText.setText("");
        
        
    }                                       

    private void cbUserItemStateChanged(java.awt.event.ItemEvent evt) {                                        
        if (cbUser.getSelectedItem().equals("SERVER")){
        btnConnection.setText("ON");
        txtUserName.setText("SERVER");
        }else{
        btnConnection.setText("CONNECT");
        txtUserName.setText("CLIENT");
        }
        
    }                                       

    private void txtMsgTextActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void client_con(){
        try{
           String ip = JOptionPane.showInputDialog("Connection IP Address");
           client = new Socket(ip,2000);
           cbUser.setEnabled(false);
           Ser_Read = new BufferedReader(new InputStreamReader(client.getInputStream()));
           Ser_Write =new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
           btnConnection.setText("Disconnected");         
        } catch (UnknownException ex){
            System.out.println("AcceptFailed");
            System.exit(-1);
        } catch (IOException ex){
            Logger.getLogger(Chat_Window.class.getName()).log(Level.SEVERE,null, ex);
        }
    }
    
    private void read_con(){
        try{
            try{
                try{
                    server = new ServerSocket(2000);
                    this.setTitle("Please wait....");                    
                } catch (IOException ex) {
                    System.out.println("Culd not listen");
                    System.exit(-1);
                }
                client = server.accept();
                this.setTitle("CONNECTED" + client.getInetAddress());
            } catch (IOException ex) {
                System.out.println("AcceptFailed");
                System.exit(-1);
            } 
            Ser_Read = new BufferedReader( new InputStreamReader(client.getInputStream()));
            Ser_Write = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch(IOException ex) {
            System.out.println("Read Failed");
            System.exit(-1);
        }
    }
    
    private void discon(){
        try{
            client.close();
            Ser_Read.close();
            cbUser.setEnabled(true);
            btnConnection.setText("CONNECT");
            } catch (IOException ex) {
                Logger.getLogger(Chat_Window.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void stopped_by_server(){
        try {
            Ser_Read.close();
            Ser_Write.close();
            btnConnection.setText("ON");
            setTitle("DISCONNECT");
           } catch (IOException ex) {
                Logger.getLogger(Chat_Window.class.getName()).log(Level.SEVERE,null, ex);
                
           }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Chat_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chat_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chat_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chat_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(){
            new Chat_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextArea Lchat;
    private javax.swing.JButton btnConnection;
    private javax.swing.JComboBox<String> cbUser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton msgSend;
    private javax.swing.JTextField txtMsgText;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration                   


//
    @Override
public void run(){
while(true){
    try {
        Lchat.append("\n"+Ser_Read.readLine());
    } catch (IOException ex) {
        Logger.getLogger(Chat_Window.class.getName()).log(Level.SEVERE, null, ex);
    }
}}

   
}

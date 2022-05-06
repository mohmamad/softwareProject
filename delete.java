
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author fanan
 */
public class delete extends javax.swing.JFrame implements ActionListener {

    ArrayList <Integer> bl;
    public delete() {
        this.bl = new ArrayList<Integer>();
        initComponents();
       try{
        Connection con2 =conn.getConncetion();
                con2.setAutoCommit(false);
                System.out.println("hi1");
                int iid=Integer.parseInt(value.getText());
                String query = "select distinct  bsq from booking_user where idn="+iid;

                Statement ps2= con2.createStatement();
                System.out.println("hi2");
                ps2.executeUpdate(query);

                ResultSet rs2=ps2.executeQuery(query);
                System.out.println("hi1");
                this.getContentPane().setLayout(new FlowLayout());
                while(rs2.next()){
                    
                int f= Integer.parseInt(rs2.getString("BSQ"));
                    bl.add(f);
                }
                 
                con2.close();}
        catch(Exception v){
            
        }
        int count=0;
           bo.removeAll();
            offer.setLayout(new GridLayout(10,4));
       
        for(int i=0;i< bl.size();i++){
           
            try {
                Connection con =conn.getConncetion();
                con.setAutoCommit(false);
                System.out.println("hi1");
                String query = "select distinct offer.OSQ,offer.date_to,offer.date_from,offer.to_des,offer.from_des, booking.hname,booking.room_type,booking.aname,booking.classd,booking.totcost from offer,booking where   booking.ido=offer.osq and bsq="+bl.get(i);

                Statement ps= con.createStatement();
                System.out.println("hi2");
                ps.executeUpdate(query);

                ResultSet rs=ps.executeQuery(query);
                System.out.println("hi1");
                this.getContentPane().setLayout(new FlowLayout());
                while(rs.next()){
                    System.out.println("hi3");
                    tod =rs.getString("Date_to");
                    fromd = rs.getString("Date_from");
                 
                    todes= rs.getString("to_Des");
                    fromdes= rs.getString("From_des");
                    System.out.println("hi35");
                    id=Integer.parseInt(rs.getString("OSQ"));

                    classd= Integer.parseInt(rs.getString("classd"));
                   
                    
                    hotel=rs.getString("hname");
                    airport=rs.getString("aname");
                    room=rs.getString("room_type");
                    System.out.println("hi34");

                    //JTextPane t1= new JTextPane ();
                    //               System.out.println(id+hotel+airport);
                    //               this.appendToPane(t1,"From Date  :"+fromd+"\n");

                    java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromd);
                    java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tod);
                    String []aa=fromd.split(" ");
                    String []bb=tod.split(" ");
                    LocalDate dd1=LocalDate.parse(aa[0]);
                    LocalDate dd2=LocalDate.parse(bb[0]);
                    long day= ChronoUnit.DAYS.between(dd2, dd1);
                    System.out.println(day);
                    cost = day*hprice +tprice+sprices;
                    cost=cost+cost*.2;

                    
                        JTextArea t1= new JTextArea ();
//                        t1.append("From Date  :"+fromd+"\n");

//                        t1.append("From Date  :"+fromd+"\n");
//                        t1.append("To Date  :"+tod+"\n");
//                        t1.append(" From :"+fromdes+"\n");
//                        t1.append("To  :"+todes+"\n");
//                        t1.append("Hotel Name :"+hotel+"\n");
//                        t1.append("Room Type :"+room+"\n");
//                        t1.append("Airport name :"+airport+"\n");
//                        t1.append("Flight Class :"+classd+"\n");
//                        t1.append("Discription :"+descrp+"\n");
//                        t1.append("Total Cost :"+cost+"\n");

                        t1.setName(count+"");
                        count++;

                        JButton b= new JButton ("Delete");
                        //              p.setPreferredSize(new Dimension(350,190));
                                     JButton b2= new JButton ("Show Details");

                    //              p.setPreferredSize(new Dimension(350,190));
                               JPanel p= new JPanel (new BorderLayout());
b.setPreferredSize(new Dimension(20,20));
b2.setPreferredSize(new Dimension(20,20));
JPanel pb = new JPanel(new GridLayout(3,1));
JTextArea tt= new JTextArea();
        tt.setText("From :"+fromdes+"    \t"+"To :"+todes+"\t"+"Cost :"+cost);
        pb.add(tt);
 pb.add(b2);
pb.add(b);

                    p.add(t1,BorderLayout.CENTER);
                    p.add(pb,BorderLayout.SOUTH);
                   
                      String tip=
                             
                            "From Date  :"+fromd+"\n"+
                    "To Date  :"+tod+"\n"+
                  " From :"+fromdes+"\n"+
                    "To  :"+todes+"\n"+
                   "Hotel Name :"+hotel+"\n"+
                    "Room Type :"+room+"\n"+
                    "Airport name :"+airport+"\n"+
                    "Flight Class :"+classd+"\n"+
                   "Discription :"+descrp+"\n";
                       
                    
//                    settip(p,tip);
                    bo.add(p);
                  

                  this.add(bo,BorderLayout.CENTER);
                    this.revalidate();
                    this.repaint();
                    b2.addActionListener(this);
                    b2.setActionCommand("dt/"+tip);
                      b.addActionListener(this);
                     b.setActionCommand("d/"+bl.get(i)+" "+id+" "+hotel+" "+room+" "+airport+" "+classd+" "+cost);
                    

                    //               t1.insert(airport,300);
                    //               t1.insert(classd+"",400);
                    //               t1.insert(room,5000);
                    //               t1.insert(descrp,600);
                    //               t1.insert(fromdes,700);
                    //               t1.insert(todes,800);

                    //               listt.add(t1);
                    //               GridBagConstraints gtf= new GridBagConstraints();
                    //               gtf.fill=GridBagConstraints.HORIZONTAL;
                    //               gtf.weightx=.5;
                    //               gtf.gridx=0;
                    //               gtf.gridwidth=2;
                    //               gtf.gridy=3+listt.size();
                    //               add (t1,gtf);
                    //               revalidate();
                    //               repaint();

                    //              t1.setText(id+"");
                    //              this.getContentPane().setLayout(new FlowLayout());
                    //                this.add(t1);

                }

                // JOptionPane.showMessageDialog(null, "no");

                con.close();
            } catch (Exception ex) {
                System.out.println(ex);
    }
        }
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    JPanel offer = new JPanel();
       
    String tod;
String fromd;
String descrp;
String todes;
String fromdes;
String hotel;
String airport;
int id;
String room;
double cost;
int classd;
int hprice;
int tprice;
int sprices;
int ssq;
JPanel bo = new JPanel(new GridLayout(3,10,5,5));
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        value = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        value.setBorder(new javax.swing.border.MatteBorder(null));
        value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valueActionPerformed(evt);
            }
        });

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 179, Short.MAX_VALUE)
                        .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(427, 427, 427))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(59, Short.MAX_VALUE)
                .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valueActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
//        try{
//        Connection con2 =conn.getConncetion();
//                con2.setAutoCommit(false);
//                System.out.println("hi1");
//                int iid=Integer.parseInt(value.getText());
//                String query = "select distinct  bsq from booking_user where idn="+iid;
//
//                Statement ps2= con2.createStatement();
//                System.out.println("hi2");
//                ps2.executeUpdate(query);
//
//                ResultSet rs2=ps2.executeQuery(query);
//                System.out.println("hi1");
//                this.getContentPane().setLayout(new FlowLayout());
//                while(rs2.next()){
//                    
//                int f= Integer.parseInt(rs2.getString("BSQ"));
//                    bl.add(f);
//                }
//                 
//                con2.close();}
//        catch(Exception v){
//            
//        }
//        int count=0;
//           bo.removeAll();
//            offer.setLayout(new GridLayout(10,4));
//       
//        for(int i=0;i< bl.size();i++){
//           
//            try {
//                Connection con =conn.getConncetion();
//                con.setAutoCommit(false);
//                System.out.println("hi1");
//                String query = "select distinct offer.OSQ,offer.date_to,offer.date_from,offer.to_des,offer.from_des, booking.hname,booking.room_type,booking.aname,booking.classd,booking.totcost from offer,booking where   booking.ido=offer.osq and bsq="+bl.get(i);
//
//                Statement ps= con.createStatement();
//                System.out.println("hi2");
//                ps.executeUpdate(query);
//
//                ResultSet rs=ps.executeQuery(query);
//                System.out.println("hi1");
//                this.getContentPane().setLayout(new FlowLayout());
//                while(rs.next()){
//                    System.out.println("hi3");
//                    tod =rs.getString("Date_to");
//                    fromd = rs.getString("Date_from");
//                 
//                    todes= rs.getString("to_Des");
//                    fromdes= rs.getString("From_des");
//                    System.out.println("hi35");
//                    id=Integer.parseInt(rs.getString("OSQ"));
//
//                    classd= Integer.parseInt(rs.getString("classd"));
//                    
//                    
//                    hotel=rs.getString("hname");
//                    airport=rs.getString("aname");
//                    room=rs.getString("room_type");
//                    System.out.println("hi34");
//
//                    //JTextPane t1= new JTextPane ();
//                    //               System.out.println(id+hotel+airport);
//                    //               this.appendToPane(t1,"From Date  :"+fromd+"\n");
//
//                    java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromd);
//                    java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tod);
//                    String []aa=fromd.split(" ");
//                    String []bb=tod.split(" ");
//                    LocalDate dd1=LocalDate.parse(aa[0]);
//                    LocalDate dd2=LocalDate.parse(bb[0]);
//                    long day= ChronoUnit.DAYS.between(dd2, dd1);
//                    System.out.println(day);
//                    cost = day*hprice +tprice+sprices;
//                    cost=cost+cost*.2;
//
//                    
//                        JTextArea t1= new JTextArea ();
////                        t1.append("From Date  :"+fromd+"\n");
//
////                        t1.append("From Date  :"+fromd+"\n");
////                        t1.append("To Date  :"+tod+"\n");
////                        t1.append(" From :"+fromdes+"\n");
////                        t1.append("To  :"+todes+"\n");
////                        t1.append("Hotel Name :"+hotel+"\n");
////                        t1.append("Room Type :"+room+"\n");
////                        t1.append("Airport name :"+airport+"\n");
////                        t1.append("Flight Class :"+classd+"\n");
////                        t1.append("Discription :"+descrp+"\n");
////                        t1.append("Total Cost :"+cost+"\n");
//
//                        t1.setName(count+"");
//                        count++;
//
//                        JButton b= new JButton ("Delete");
//                        //              p.setPreferredSize(new Dimension(350,190));
//                                     JButton b2= new JButton ("Show Details");
//
//                    //              p.setPreferredSize(new Dimension(350,190));
//                               JPanel p= new JPanel (new BorderLayout());
//b.setPreferredSize(new Dimension(20,20));
//b2.setPreferredSize(new Dimension(20,20));
//JPanel pb = new JPanel(new GridLayout(3,1));
//JTextArea tt= new JTextArea();
//        tt.setText("From :"+fromdes+"    \t"+"To :"+todes+"\t"+"Cost :"+cost);
//        pb.add(tt);
// pb.add(b2);
//pb.add(b);
//
//                    p.add(t1,BorderLayout.CENTER);
//                    p.add(pb,BorderLayout.SOUTH);
//                   
//                      String tip=
//                             
//                            "From Date  :"+fromd+"\n"+
//                    "To Date  :"+tod+"\n"+
//                  " From :"+fromdes+"\n"+
//                    "To  :"+todes+"\n"+
//                   "Hotel Name :"+hotel+"\n"+
//                    "Room Type :"+room+"\n"+
//                    "Airport name :"+airport+"\n"+
//                    "Flight Class :"+classd+"\n"+
//                   "Discription :"+descrp+"\n";
//                       
//                    
////                    settip(p,tip);
//                    bo.add(p);
//                  
//
//                  this.add(bo,BorderLayout.CENTER);
//                    this.revalidate();
//                    this.repaint();
//                    b2.addActionListener(this);
//                    b2.setActionCommand("dt/"+tip);
//                      b.addActionListener(this);
//                    b.setActionCommand("d/"+bl.get(i)+" "+id+" "+hotel+" "+room+" "+airport+" "+classd+" "+cost);
//                    
//
//                    //               t1.insert(airport,300);
//                    //               t1.insert(classd+"",400);
//                    //               t1.insert(room,5000);
//                    //               t1.insert(descrp,600);
//                    //               t1.insert(fromdes,700);
//                    //               t1.insert(todes,800);
//
//                    //               listt.add(t1);
//                    //               GridBagConstraints gtf= new GridBagConstraints();
//                    //               gtf.fill=GridBagConstraints.HORIZONTAL;
//                    //               gtf.weightx=.5;
//                    //               gtf.gridx=0;
//                    //               gtf.gridwidth=2;
//                    //               gtf.gridy=3+listt.size();
//                    //               add (t1,gtf);
//                    //               revalidate();
//                    //               repaint();
//
//                    //              t1.setText(id+"");
//                    //              this.getContentPane().setLayout(new FlowLayout());
//                    //                this.add(t1);
//
//                }
//
//                // JOptionPane.showMessageDialog(null, "no");
//
//                con.close();
//            } catch (Exception ex) {
//                System.out.println(ex);
//    }
//        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(delete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new delete().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField value;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of generated methods, choose Tools | Templates.
         String ac= e.getActionCommand();
       String []ids= ac.split("/");
       
       if(ids[0].equals("dt")){
         
            JOptionPane.showMessageDialog(rootPane, ids[1]);
       }
       else{
          
               
               //  b.setActionCommand("b/"+id+" "+hotel+" "+room+" "+airport+" "+classd+" "+cost);
               String [] info =ids[1].split(" ");
               
               
              
               
            
               System.out.println(Double.parseDouble(info[5]));
               double fcost=Double.parseDouble(info[5]);
              
              System.out.println(info[0]+" "+info[1]+" "+info[2]+" "+info[3]+" "+info[4]+" "+fcost);
              
              
                    try{
        Connection con2 =conn.getConncetion();
                con2.setAutoCommit(false);
                System.out.println("hi1");
                int iid=Integer.parseInt(value.getText());
                String query = "delete from booking_user  where idn="+iid+" and bsq="+info[0];

                Statement ps2= con2.createStatement();
                System.out.println("hi2");
                ps2.executeUpdate(query); 
                con2.commit();
                con2.close();
                    }
                    catch(Exception exx){
                        
                    }
               
               
               
           }
       }
           
    }

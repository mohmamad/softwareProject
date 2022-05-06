
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
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
public class finals extends javax.swing.JFrame implements ActionListener{

    /**
     * Creates new form finals
     */
    public finals() {
        bo.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        initComponents();
         DefaultListModel dlm= new DefaultListModel();
        try{
        Connection con2 =conn.getConncetion();
                con2.setAutoCommit(false);
                System.out.println("hi1");
                String query = "select distinct  services.ssq,services.des,services.cost1 from services where ssq >2";

                Statement ps2= con2.createStatement();
                System.out.println("hi2");
                ps2.executeUpdate(query);

                ResultSet rs2=ps2.executeQuery(query);
                System.out.println("hi1");
                this.getContentPane().setLayout(new FlowLayout());
                while(rs2.next()){
                    sprices= Integer.parseInt(rs2.getString("cost1"));
                      ssq= Integer.parseInt(rs2.getString("ssq"));
                   
                    descrp= rs2.getString("Des");
                   
                    
                    String dd=ssq+"/"+descrp+"/"+sprices;
                    dlm.addElement(dd);
                 
                }
                  ser.setModel(dlm);
                con2.close();}
        catch(Exception v){
            
        }
                
          
        
         int count=0;
           bo.removeAll();
            offer.setLayout(new GridLayout(10,4));
            try {
                Connection con =conn.getConncetion();
                con.setAutoCommit(false);
                System.out.println("hi1");
                String query = "select distinct offer.OSQ,hotel.hprice,airport.tprice,offer.date_to,offer.date_from,offer.to_des,offer.from_des, hotoffer.hname,hotoffer.room_type,airoffer.aname,airoffer.classd from offer,hotoffer,airoffer,airport,hotel where   hotoffer.ido=offer.osq and airoffer.ido=offer.osq and hotel.hname=hotoffer.hname and airport.aname=airoffer.aname and airport.classd=airoffer.classd and hotoffer.room_type=hotel.room_type";

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
                    hprice= Integer.parseInt(rs.getString("hprice"));
                    tprice= Integer.parseInt(rs.getString("tprice"));
                    
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

                        JButton b= new JButton ("BOOK OFFER");
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
                    b.setActionCommand("b/"+id+" "+hotel+" "+room+" "+airport+" "+classd+" "+cost);
                    

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
                // Logger.getLogger(employy.class.getName()).log(Level.SEVERE, null, ex);
                //

            }
    }
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        filter = new javax.swing.JComboBox<>();
        value = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ser = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        filter.setBackground(new java.awt.Color(51, 153, 255));
        filter.setFont(new java.awt.Font("Goudy Old Style", 1, 18)); // NOI18N
        filter.setForeground(new java.awt.Color(51, 0, 255));
        filter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date", "to", "from", "Up to cost" }));
        filter.setBorder(new javax.swing.border.MatteBorder(null));
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });

        value.setBorder(new javax.swing.border.MatteBorder(null));
        value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valueActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(51, 153, 255));
        jButton1.setFont(new java.awt.Font("Goudy Old Style", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 0, 255));
        jButton1.setText("Search");
        jButton1.setBorder(new javax.swing.border.MatteBorder(null));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(ser);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(110, Short.MAX_VALUE)
                        .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(487, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterActionPerformed

    private void valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valueActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (filter.getSelectedItem().equals("Date")){

            int count=0;
            bo.removeAll();
            offer.setLayout(new GridLayout(10,4));

            try {
                Connection con =conn.getConncetion();
                con.setAutoCommit(false);
                System.out.println("hi1");
                String query = "select distinct  offer.OSQ,hotel.hprice,airport.tprice,offer.date_to,offer.date_from,offer.to_des,offer.from_des, hotoffer.hname,hotoffer.room_type,airoffer.aname,airoffer.classd from offer,hotoffer,airoffer,airport,hotel where offer.date_from='"+value.getText()+"' and   hotoffer.ido=offer.osq and airoffer.ido=offer.osq and hotel.hname=hotoffer.hname and airport.aname=airoffer.aname and airport.classd=airoffer.classd and hotoffer.room_type=hotel.room_type";

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
                    hprice= Integer.parseInt(rs.getString("hprice"));
                    tprice= Integer.parseInt(rs.getString("tprice"));
                   
                    hotel=rs.getString("hname");
                    airport=rs.getString("aname");
                    room=rs.getString("room_type");
                    System.out.println("hi34");

                    JTextArea t1= new JTextArea ();
                    //JTextPane t1= new JTextPane ();
                    //               System.out.println(id+hotel+airport);
                    //               this.appendToPane(t1,"From Date  :"+fromd+"\n");

//                    t1.append("From Date  :"+fromd+"\n");
//
//                    t1.append("From Date  :"+fromd+"\n");
//                    t1.append("To Date  :"+tod+"\n");
//                    t1.append(" From :"+fromdes+"\n");
//                    t1.append("To  :"+todes+"\n");
//                    t1.append("Hotel Name :"+hotel+"\n");
//                    t1.append("Room Type :"+room+"\n");
//                    t1.append("Airport name :"+airport+"\n");
//                    t1.append("Flight Class :"+classd+"\n");
//                    t1.append("Discription :"+descrp+"\n");

                    java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromd);
                    java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tod);
                    String []aa=fromd.split(" ");
                    String []bb=tod.split(" ");
                    LocalDate dd1=LocalDate.parse(aa[0]);
                    LocalDate dd2=LocalDate.parse(bb[0]);
                    long day= ChronoUnit.DAYS.between(dd2, dd1);
                    System.out.println(day);
                    cost = day*hprice +tprice;
                    cost=cost+cost*.2;
//                    t1.append("Total Cost :"+cost+"\n");

                    t1.setName(count+"");
                    count++;
JButton b= new JButton ("Book offer");
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
                    b.setActionCommand("b/"+id+" "+hotel+" "+room+" "+airport+" "+classd+" "+cost);

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
                // Logger.getLogger(employy.class.getName()).log(Level.SEVERE, null, ex);
                //

            }
        }

        //        if (filter.getSelectedItem().equals("from")){
            //               int count=0;
            //                    p.removeAll();
            //
            //                   try {
                //            Connection con =conn.getConncetion();
                //            con.setAutoCommit(false);
                // System.out.println("hi1");
                //            String query = "select distinct  offer.OSQ,hotel.hprice,airport.tprice,offer.descrp,offer.date_to,offer.date_from,offer.to_des,offer.from_des, hotoffer.hname,hotoffer.room_type,airoffer.aname,airoffer.classd from offer,hotoffer,airoffer,airport,hotel,services,seroffer where offer.from_Des='"+value.getText()+"' and hotoffer.ido=offer.osq and airoffer.ido=offer.osq and hotel.hname=hotoffer.hname and airport.aname=airoffer.aname and airport.classd=airoffer.classd and hotoffer.room_type=hotel.room_type";
                //
                //            Statement ps= con.createStatement();
                //System.out.println("hi2");
                //            ps.executeUpdate(query);
                //
                //            ResultSet rs=ps.executeQuery(query);
                //             System.out.println("hi1");
                //               this.getContentPane().setLayout(new FlowLayout());
                //           while(rs.next()){
                    // System.out.println("hi3");
                    //                tod =rs.getString("Date_to");
                    //                fromd = rs.getString("Date_from");
                    //                descrp= rs.getString("Descrp");
                    //                todes= rs.getString("to_Des");
                    //                fromdes= rs.getString("From_des");
                    //                System.out.println("hi35");
                    //                id=Integer.parseInt(rs.getString("OSQ"));
                    //
                    //                classd= Integer.parseInt(rs.getString("classd"));
                    //                hprice= Integer.parseInt(rs.getString("hprice"));
                    //               tprice= Integer.parseInt(rs.getString("tprice"));
                    //                hotel=rs.getString("hname");
                    //                airport=rs.getString("aname");
                    //                room=rs.getString("room_type");
                    //                 System.out.println("hi34");
                    //
                    //               JTextArea t1= new JTextArea ();
                    ////JTextPane t1= new JTextPane ();
                    ////               System.out.println(id+hotel+airport);
                    ////               this.appendToPane(t1,"From Date  :"+fromd+"\n");
                    //
                    //             t1.append("From Date  :"+fromd+"\n");
                    //
                    //                t1.append("From Date  :"+fromd+"\n");
                    //               t1.append("To Date  :"+tod+"\n");
                    //                t1.append(" From :"+fromdes+"\n");
                    //                t1.append("To  :"+todes+"\n");
                    //                t1.append("Hotel Name :"+hotel+"\n");
                    //                 t1.append("Room Type :"+room+"\n");
                    //                  t1.append("Airport name :"+airport+"\n");
                    //                   t1.append("Flight Class :"+classd+"\n");
                    //                    t1.append("Discription :"+descrp+"\n");
                    //
                    //
                    //                   java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromd);
                    //                    java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tod);
                    //                    String []aa=fromd.split(" ");
                    //                    String []bb=tod.split(" ");
                    //                 LocalDate dd1=LocalDate.parse(aa[0]);
                    //                    LocalDate dd2=LocalDate.parse(bb[0]);
                    //                   long day= ChronoUnit.DAYS.between(dd2, dd1);
                    //                    System.out.println(day);
                    //                    cost = day*hprice +tprice;
                    //                    cost=cost+cost*.2;
                    //                    t1.append("Total Cost :"+cost+"\n");
                    //
                    //
                    //                 t1.setName(count+"");
                    //               count++;
                    //              JButton b= new JButton ("BOOK OFFER");
                    //               p.add(t1);
                    //               p.add(b);
                    //               this.add(p);
                    //               this.revalidate();
                    //               this.repaint();
                    //
                    //
                    ////               t1.insert(airport,300);
                    ////               t1.insert(classd+"",400);
                    ////               t1.insert(room,5000);
                    ////               t1.insert(descrp,600);
                    ////               t1.insert(fromdes,700);
                    ////               t1.insert(todes,800);
                    //
                    ////               listt.add(t1);
                    ////               GridBagConstraints gtf= new GridBagConstraints();
                    ////               gtf.fill=GridBagConstraints.HORIZONTAL;
                    ////               gtf.weightx=.5;
                    ////               gtf.gridx=0;
                    ////               gtf.gridwidth=2;
                    ////               gtf.gridy=3+listt.size();
                    ////               add (t1,gtf);
                    ////               revalidate();
                    ////               repaint();
                    //
                    ////              t1.setText(id+"");
                    ////              this.getContentPane().setLayout(new FlowLayout());
                    ////                this.add(t1);
                    //
                    //
                    //            }
                //
                //            // JOptionPane.showMessageDialog(null, "no");
                //
                //            con.close();
                //        } catch (Exception ex) {
                //            System.out.println(ex);
                //           // Logger.getLogger(employy.class.getName()).log(Level.SEVERE, null, ex);
                ////
                //
                //        }
            //

            //        }
        if (filter.getSelectedItem().equals("to")){
            int count=0;

            bo.removeAll();
          
            this.revalidate();
            this.repaint();

            try {
                Connection con =conn.getConncetion();
                con.setAutoCommit(false);
                System.out.println("hi1");
                String query = "select distinct  offer.OSQ,hotel.hprice,airport.tprice,offer.date_to,offer.date_from,offer.to_des,offer.from_des, hotoffer.hname,hotoffer.room_type,airoffer.aname,airoffer.classd from offer,hotoffer,airoffer,airport,hotel where offer.to_des='"+value.getText()+"' and  hotoffer.ido=offer.osq and airoffer.ido=offer.osq and hotel.hname=hotoffer.hname and airport.aname=airoffer.aname and airport.classd=airoffer.classd and hotoffer.room_type=hotel.room_type";

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
                    hprice= Integer.parseInt(rs.getString("hprice"));
                    tprice= Integer.parseInt(rs.getString("tprice"));
                    
                    hotel=rs.getString("hname");
                    airport=rs.getString("aname");
                    room=rs.getString("room_type");
                    System.out.println("hi34");

                    JTextArea t1= new JTextArea ();
                    //JTextPane t1= new JTextPane ();
                    //               System.out.println(id+hotel+airport);
                    //               this.appendToPane(t1,"From Date  :"+fromd+"\n");

//                    t1.append("From Date  :"+fromd+"\n");
//
//                    t1.append("From Date  :"+fromd+"\n");
//                    t1.append("To Date  :"+tod+"\n");
//                    t1.append(" From :"+fromdes+"\n");
//                    t1.append("To  :"+todes+"\n");
//                    t1.append("Hotel Name :"+hotel+"\n");
//                    t1.append("Room Type :"+room+"\n");
//                    t1.append("Airport name :"+airport+"\n");
//                    t1.append("Flight Class :"+classd+"\n");
//                    t1.append("Discription :"+descrp+"\n");

                    java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromd);
                    java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tod);
                    String []aa=fromd.split(" ");
                    String []bb=tod.split(" ");
                    LocalDate dd1=LocalDate.parse(aa[0]);
                    LocalDate dd2=LocalDate.parse(bb[0]);
                    long day= ChronoUnit.DAYS.between(dd2, dd1);
                    System.out.println(day);
                    cost = day*hprice +tprice;
                    cost=cost+cost*.2;
//                    t1.append("Total Cost :"+cost+"\n");

                    t1.setName(count+"");
                    count++;

                    JButton b= new JButton ("BOOK OFFER");
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
                    b.setActionCommand("b/"+id+" "+hotel+" "+room+" "+airport+" "+classd+" "+cost);

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
                // Logger.getLogger(employy.class.getName()).log(Level.SEVERE, null, ex);
                //

            }
        }

        if (filter.getSelectedItem().equals("from")){
            int count=0;
            int i=0;
            int j=0;

           bo.removeAll();
            offer.setLayout(new GridLayout(20,20));
            this.revalidate();
            this.repaint();

            try {
                Connection con =conn.getConncetion();
                con.setAutoCommit(false);
                System.out.println("hi1");
                String query = "select distinct  offer.OSQ,hotel.hprice,airport.tprice,offer.date_to,offer.date_from,offer.to_des,offer.from_des, hotoffer.hname,hotoffer.room_type,airoffer.aname,airoffer.classd from offer,hotoffer,airoffer,airport,hotel where offer.from_des='"+value.getText()+"' and hotoffer.ido=offer.osq and airoffer.ido=offer.osq and hotel.hname=hotoffer.hname and airport.aname=airoffer.aname and airport.classd=airoffer.classd and hotoffer.room_type=hotel.room_type";

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
                    hprice= Integer.parseInt(rs.getString("hprice"));
                    tprice= Integer.parseInt(rs.getString("tprice"));
                     
                    hotel=rs.getString("hname");
                    airport=rs.getString("aname");
                    room=rs.getString("room_type");
                    System.out.println("hi34");

                    JTextArea t1= new JTextArea ();
                    //JTextPane t1= new JTextPane ();
                    //               System.out.println(id+hotel+airport);
                    //               this.appendToPane(t1,"From Date  :"+fromd+"\n");

//                    t1.append("From Date  :"+fromd+"\n");
//
//                    t1.append("From Date  :"+fromd+"\n");
//                    t1.append("To Date  :"+tod+"\n");
//                    t1.append(" From :"+fromdes+"\n");
//                    t1.append("To  :"+todes+"\n");
//                    t1.append("Hotel Name :"+hotel+"\n");
//                    t1.append("Room Type :"+room+"\n");
//                    t1.append("Airport name :"+airport+"\n");
//                    t1.append("Flight Class :"+classd+"\n");
//                    t1.append("Discription :"+descrp+"\n");

                    java.util.Date d1 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromd);
                    java.util.Date d2 =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(tod);
                    String []aa=fromd.split(" ");
                    String []bb=tod.split(" ");
                    LocalDate dd1=LocalDate.parse(aa[0]);
                    LocalDate dd2=LocalDate.parse(bb[0]);
                    long day= ChronoUnit.DAYS.between(dd2, dd1);
                    System.out.println(day);
                    cost = day*hprice +tprice;
                    cost=cost+cost*.2;
//                    t1.append("Total Cost :"+cost+"\n");

                    t1.setName(count+"");
                    count++;
                    JButton b= new JButton ("BOOK OFFER");
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
                    b.setActionCommand("b/"+id+" "+hotel+" "+room+" "+airport+" "+classd+" "+cost);

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
                System.out.println(ex.getStackTrace());
                // Logger.getLogger(employy.class.getName()).log(Level.SEVERE, null, ex);
                //

            }

        }
        if (filter.getSelectedItem().equals("Up to cost")){
            int count=0;
           bo.removeAll();
            offer.setLayout(new GridLayout(10,4));
            try {
                Connection con =conn.getConncetion();
                con.setAutoCommit(false);
                System.out.println("hi1");
                String query = "select distinct  offer.OSQ,hotel.hprice,airport.tprice,offer.date_to,offer.date_from,offer.to_des,offer.from_des, hotoffer.hname,hotoffer.room_type,airoffer.aname,airoffer.classd from offer,hotoffer,airoffer,airport,hotel where    hotoffer.ido=offer.osq and airoffer.ido=offer.osq and hotel.hname=hotoffer.hname and airport.aname=airoffer.aname and airport.classd=airoffer.classd and hotoffer.room_type=hotel.room_type";

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
                    hprice= Integer.parseInt(rs.getString("hprice"));
                    tprice= Integer.parseInt(rs.getString("tprice"));
                     
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
                    cost = day*hprice +tprice;
                    cost=cost+cost*.2;

                    if(cost<=Integer.parseInt(value.getText())){
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

                        JButton b= new JButton ("BOOK OFFER");
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
                    b.setActionCommand("b/"+id+" "+hotel+" "+room+" "+airport+" "+classd+" "+cost);
                    }

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
                // Logger.getLogger(employy.class.getName()).log(Level.SEVERE, null, ex);
                //

            }

        }
    
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(finals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(finals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(finals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(finals.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new finals().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> filter;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> ser;
    private javax.swing.JTextField value;
    // End of variables declaration//GEN-END:variables

 @Override
    public void actionPerformed(ActionEvent e) {
     
       String ac= e.getActionCommand();
       String []ids= ac.split("/");
       
       if(ids[0].equals("dt")){
         
            JOptionPane.showMessageDialog(rootPane, ids[1]);
       }
       else{
           int[] ind= ser.getSelectedIndices();
           int l=ind.length;
           if(l==0){
               JOptionPane.showMessageDialog(rootPane, "please Choose a services from list above for no service choose -");
           }
           else{
               
               //  b.setActionCommand("b/"+id+" "+hotel+" "+room+" "+airport+" "+classd+" "+cost);
               String [] info =ids[1].split(" ");
               
               for(int i=0;i<l;i++){
               String serinfo =ser.getModel().getElementAt(ind[i]);
               String [] newinfo = serinfo.split("/");
            
               System.out.println(Double.parseDouble(info[5]));
               double fcost=Double.parseDouble(info[5]);
              fcost+=Integer.parseInt(newinfo[2]);
              System.out.println(info[0]+" "+info[1]+" "+info[2]+" "+info[3]+" "+info[4]+" "+fcost);
              
              
                       }
               
               
               
           }
           
       }
//         System.out.println(ac);
//        System.out.println(ids[1]);
        
          
      
 
  
  }
    public static void settip(JComponent c,String t){
      c.setToolTipText(t);
      for(Component cc: c.getComponents()){
          if (cc instanceof JComponent )
              settip((JComponent)cc, t
              );      }
        
    }

}

/**
 * @author Maik Basso <maik@maikbasso.com.br>
 */

package ndvi.viewer;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageViewer extends javax.swing.JFrame implements MouseListener, MouseMotionListener  {
    
    private NDVICalc ndviCalc;
    private final JLabel jlab;
    private int mouseX = 0;
    private int mouseY = 0;
    
    public ImageViewer() {
        initComponents();
        //label image
        this.jlab = new JLabel();
        
        this.jlab.addMouseListener( this );        // listens for own mouse and
        this.jlab.addMouseMotionListener( this );
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(size);
    }
    
    private void calcNDVI(int x1, int y1, int x2, int y2){
        //prevents values minor the zero
        if(x1<0)x1 = 0;
        if(y1<0)y1 = 0;
        if(x2<0)x2 = 0;
        if(y2<0)y2 = 0;
        
        //Reverses the coordinates for all selection modes
        if(x1>x2 || y1>y2){
            int auxX = 0;
            int auxY = 0;
            auxX = x1;
            auxY = y1;
            x1 = x2;
            y1 = y2;
            x2 = auxX;
            y2 = auxY;
        }
        if(x1>x2 && y1<y2){
            int aux = x1;
            x1 = x2;
            x2 = aux;
        }
        if(x1<x2 && y1>y2){
            int aux = y1;
            y1 = y2;
            y2 = aux;
        }
        
        if(x1==x2 && y1==y2){
            jLResults.setText("Click and drag the mouse to calculate NDVI.");
        }
        else{
            jLResults.setText("GNDVI: " + this.ndviCalc.getGNDVI(x1,y1,x2,y2) + " | BNDVI: " + this.ndviCalc.getBNDVI(x1,y1,x2,y2) + " | (x1: "+x1+", y1: "+y1+")(x2: "+x2+", y2: "+y2+")");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLResults = new javax.swing.JLabel();
        jBCalcForAllImage = new javax.swing.JButton();
        jLMousePosition = new javax.swing.JLabel();
        jSP = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMIOpen = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NDVI Viewer 1.0 by Maik Basso <maik@maikbasso.com.br>");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLResults.setText("Open an infrared image to view NDVI values.");

        jBCalcForAllImage.setText("Calc for all image");
        jBCalcForAllImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBCalcForAllImageActionPerformed(evt);
            }
        });

        jLMousePosition.setText("Mouse position:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jBCalcForAllImage)
                .addGap(18, 18, 18)
                .addComponent(jLMousePosition, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(jLResults)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLMousePosition)
                    .addComponent(jBCalcForAllImage)
                    .addComponent(jLResults))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu1.setText("File");

        jMIOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMIOpen.setText("Open Infrared Image");
        jMIOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIOpenActionPerformed(evt);
            }
        });
        jMenu1.add(jMIOpen);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSP)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSP, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jMIOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIOpenActionPerformed
        JFileChooser jfc = new JFileChooser();
        //select file and display
        if(jfc.showOpenDialog(jMenu1) == JFileChooser.APPROVE_OPTION){
            //get selected file name [i.e., complete file path]
            File f = jfc.getSelectedFile();
            
            //set icon
            this.jlab.setIcon(new ImageIcon(f.toString()));
            
            //alignment
            this.jlab.setHorizontalAlignment(JLabel.CENTER);
            
            //add jLabel to scroll pane
            jSP.getViewport().add(this.jlab);
            
            try {
                //load image data
                BufferedImage buff = ImageIO.read(f);
                
                this.ndviCalc = new NDVICalc(buff);
                
                //set results for all image
                jLResults.setText("Calculating...");
                jLResults.setText("GNDVI: " + this.ndviCalc.getGNDVI() + " BNDVI: " + this.ndviCalc.getBNDVI());
                
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }    
        }
    }//GEN-LAST:event_jMIOpenActionPerformed
    
    private void jBCalcForAllImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBCalcForAllImageActionPerformed
        //set results for all image
        if(this.ndviCalc != null){
            jLResults.setText("Calculating...");
            jLResults.setText("GNDVI: " + this.ndviCalc.getGNDVI() + " BNDVI: " + this.ndviCalc.getBNDVI());
        }
        else{
            jLResults.setText("No infrared image has selected!");
        }
    }//GEN-LAST:event_jBCalcForAllImageActionPerformed

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
                if ("GTK".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ImageViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ImageViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ImageViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ImageViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ImageViewer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBCalcForAllImage;
    private javax.swing.JLabel jLMousePosition;
    private javax.swing.JLabel jLResults;
    private javax.swing.JMenuItem jMIOpen;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jSP;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        jLMousePosition.setText("Mouse: (x: "+e.getX()+", y: "+e.getY()+")");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        jLResults.setText("Selecting...");
        jLMousePosition.setText("Mouse: (x: "+e.getX()+", y: "+e.getY()+")");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.calcNDVI(this.mouseX, this.mouseY, e.getX(), e.getY());
        jLMousePosition.setText("Mouse: (x: "+e.getX()+", y: "+e.getY()+")");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        jLMousePosition.setText("Mouse: (x: "+e.getX()+", y: "+e.getY()+")");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        jLMousePosition.setText("Mouse: (x: "+e.getX()+", y: "+e.getY()+")");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        jLMousePosition.setText("Mouse: (x: "+e.getX()+", y: "+e.getY()+")");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        jLMousePosition.setText("Mouse: (x: "+e.getX()+", y: "+e.getY()+")");
    }
}

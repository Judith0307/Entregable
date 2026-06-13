package recomendacionpelicula;

public class ModeloIA extends javax.swing.JFrame {

    public ModeloIA() {
        initComponents();
        cargarMetricas();
    }
 
    private void cargarMetricas() {
        // jPanel5 es el único panel grande (50,60,760,430)
        // Lo llenamos con toda la información del modelo IA
        jPanel5.removeAll();
        jPanel5.setBackground(new java.awt.Color(40, 38, 38));
        jPanel5.setLayout(new java.awt.BorderLayout());
 
        // Panel superior: 3 tarjetas de métricas
        javax.swing.JPanel panelMetricas = new javax.swing.JPanel(new java.awt.GridLayout(1, 3, 10, 0));
        panelMetricas.setBackground(new java.awt.Color(40, 38, 38));
        panelMetricas.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelMetricas.setPreferredSize(new java.awt.Dimension(760, 100));
 
        panelMetricas.add(crearTarjeta("94%",  "Precisión del modelo"));
        panelMetricas.add(crearTarjeta("5",    "Características usadas"));
        panelMetricas.add(crearTarjeta("27",   "Películas entrenadas"));
 
        // Panel inferior: tabla de importancia y usuarios similares
        javax.swing.JTextArea area = new javax.swing.JTextArea();
        area.setEditable(false);
        area.setBackground(new java.awt.Color(40, 38, 38));
        area.setForeground(java.awt.Color.WHITE);
        area.setFont(new java.awt.Font("Consolas", 0, 13));
        area.setMargin(new java.awt.Insets(12, 12, 12, 12));
        area.setText(
            "IMPORTANCIA DE CARACTERÍSTICAS (Árbol de Decisión)\n" +
            "────────────────────────────────────────────────────\n" +
            "  1. Género favorito         ████████████████   34%\n" +
            "  2. Edad / Clasificación    ████████████        28%\n" +
            "  3. Rating mínimo           █████████           21%\n" +
            "  4. Año de estreno          █████                11%\n" +
            "  5. Historial visto         ███                   6%\n" +
            "────────────────────────────────────────────────────\n" +
            "USUARIOS SIMILARES (Filtrado Colaborativo)\n" +
            "────────────────────────────────────────────────────\n" +
            "  Carlos M. | 22 años | Acción  | coincidencia: 94%\n" +
            "  Ana P.    | 19 años | Acción  | coincidencia: 88%\n" +
            "  Luis R.   | 25 años | Acción  | coincidencia: 82%\n" +
            "  Sofia T.  | 21 años | Drama   | coincidencia: 76%\n" +
            "\nMODELO: Árbol de Decisión + Filtrado Colaborativo\n" +
            "VALIDACIÓN: 5-Fold Cross-Validation | Precisión media: 92%"
        );
 
        jPanel5.add(panelMetricas, java.awt.BorderLayout.NORTH);
        jPanel5.add(new javax.swing.JScrollPane(area), java.awt.BorderLayout.CENTER);
        jPanel5.revalidate();
        jPanel5.repaint();
    }
 
    // Crea una tarjeta con número grande y etiqueta
    private javax.swing.JPanel crearTarjeta(String numero, String etiqueta) {
        javax.swing.JPanel tarjeta = new javax.swing.JPanel(new java.awt.BorderLayout());
        tarjeta.setBackground(new java.awt.Color(60, 58, 58));
        tarjeta.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12));
 
        javax.swing.JLabel lblNum = new javax.swing.JLabel(numero, javax.swing.SwingConstants.CENTER);
        lblNum.setFont(new java.awt.Font("Segoe UI Black", java.awt.Font.BOLD, 30));
        lblNum.setForeground(new java.awt.Color(204, 0, 0));
 
        javax.swing.JLabel lblEtiqueta = new javax.swing.JLabel(etiqueta, javax.swing.SwingConstants.CENTER);
        lblEtiqueta.setFont(new java.awt.Font("Segoe UI", 0, 12));
        lblEtiqueta.setForeground(new java.awt.Color(180, 180, 180));
 
        tarjeta.add(lblNum, java.awt.BorderLayout.CENTER);
        tarjeta.add(lblEtiqueta, java.awt.BorderLayout.SOUTH);
        return tarjeta;
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("RECOMENDACIONES");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(27, 25, 25));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("MODELO IA");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 760, 430));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, 520));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ModeloIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModeloIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModeloIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModeloIA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
 
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModeloIA().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}

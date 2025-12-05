/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Almacen.MarcaAlmacen;
import Almacen.ModeloAlmacen;
import Almacen.MotocicletaAlmacen;
import Model.Motocicleta;
import Model.Marca;
import java.awt.HeadlessException;
import java.util.List;
import java.util.logging.Level;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lobat
 */
public class Moto extends javax.swing.JFrame {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Moto.class.getName());

    // 1. DEPENDENCIAS (Inyección de DAOs)
    private final MotocicletaAlmacen motoAlmacen = new MotocicletaAlmacen();
    private final MarcaAlmacen marcaAlmacen = new MarcaAlmacen();
    private final ModeloAlmacen modeloAlmacen = new ModeloAlmacen();
    
    // 2. Variables de ESTADO
    // Lista de marcas para mapear nombres a IDs fácilmente
    private List<Marca> listaMarcas; 
    // ID de la motocicleta seleccionada para actualizar/eliminar. 0 = Modo Guardar.
    private int idMotoSeleccionada = 0;
    
    /**
     * Creates new form Moto
     */
    public Moto() {
        initComponents();
        setLocationRelativeTo(null);
        inicializarComponentesPersonalizados(); 
    }

    private void inicializarComponentesPersonalizados() {
        // Cargar primero las marcas desde la BD
        listaMarcas = marcaAlmacen.obtenerTodasLasMarcas(); 
        
        cargarTablaMotos();
        cargarComboMarcas();
        limpiarCampos();
        
        // Configuramos la tabla para escuchar selecciones
        tableMotos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tableMotos.getSelectedRow() != -1) {
                cargarMotoSeleccionada();
            }
        });
    }
    
    private void cargarTablaMotos() {
        // Definir los encabezados de la tabla con los campos del JOIN
        String[] columnNames = {"ID", "Matrícula", "Marca", "Modelo", "Año", "Color", "N° Serie"};
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            // Impedir que la tabla sea editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        List<Motocicleta> motos = motoAlmacen.obtenerTodasLasMotos(); 

        for (Motocicleta moto : motos) {
            Object[] row = new Object[7];
            row[0] = moto.getIdMoto();
            row[1] = moto.getNoMatricula();
            row[2] = moto.getMarca(); 
            row[3] = moto.getNombreModelo(); 
            row[4] = moto.getAño();
            row[5] = moto.getColor();
            row[6] = moto.getNumSerie();
            model.addRow(row);
        }
        tableMotos.setModel(model);
    }
    
    private void cargarMotoSeleccionada() {
        int row = tableMotos.getSelectedRow();
        if (row >= 0) {
            // Obtener el ID de la moto de la primera columna
            idMotoSeleccionada = (int) tableMotos.getValueAt(row, 0); 
            
            // Traer la moto completa desde el DAO (incluyendo idModelo/idCliente si se necesita)
            Motocicleta moto = motoAlmacen.obtenerMotoPorId(idMotoSeleccionada);
            
            if (moto != null) {
                // Rellenar campos
                NoMatricula.setText(moto.getNoMatricula());
                año.setText(String.valueOf(moto.getAño()));
                color.setText(moto.getColor());
                Modelo.setText(moto.getNombreModelo()); 
                
                // Seleccionar la Marca en el ComboBox
                String nombreMarcaSeleccionada = moto.getMarca();
                marca.setSelectedItem(nombreMarcaSeleccionada);
                
                guardarMoto.setText("Actualizar"); // Cambiar texto del botón
            }
        }
    }


    // -------------------------------------------------------------
    // GESTIÓN DE JCOMBOBOX (Marca)
    // -------------------------------------------------------------

    private void cargarComboMarcas() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Seleccionar Marca"); // Opción por defecto
        
        for (Marca m : listaMarcas) {
            model.addElement(m.getNombreMarca());
        }
        marca.setModel(model);
    }
    
    private int obtenerIdMarcaSeleccionada() {
        String nombreMarca = (String) marca.getSelectedItem();
        if (nombreMarca == null || nombreMarca.equals("Seleccionar Marca")) {
            return 0;
        }
        
        // Buscamos en la lista cargada al inicio para encontrar el ID
        for (Marca m : listaMarcas) {
            if (m.getNombreMarca().equals(nombreMarca)) {
                return m.getIdMarca();
            }
        }
        return 0;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        volver1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        NoMatricula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        marca = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Modelo = new javax.swing.JTextField();
        año = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        color = new javax.swing.JTextField();
        Cancelar = new javax.swing.JButton();
        eliminarMoto = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMotos = new javax.swing.JTable();
        guardarMoto = new javax.swing.JButton();

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nueva Orden");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(204, 51, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(65, 66));

        volver1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        volver1.setText("<");
        volver1.addActionListener(this::volver1ActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(volver1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(volver1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("No. Matricula");

        NoMatricula.addActionListener(this::NoMatriculaActionPerformed);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Modelo");

        marca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        marca.addActionListener(this::marcaActionPerformed);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Marca");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Año");

        Modelo.addActionListener(this::ModeloActionPerformed);

        año.addActionListener(this::añoActionPerformed);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Color");

        color.addActionListener(this::colorActionPerformed);

        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(this::CancelarActionPerformed);

        eliminarMoto.setBackground(new java.awt.Color(204, 102, 0));
        eliminarMoto.setText("Eliminar moto");
        eliminarMoto.addActionListener(this::eliminarMotoActionPerformed);

        tableMotos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tableMotos);

        guardarMoto.setBackground(new java.awt.Color(204, 102, 0));
        guardarMoto.setText("Guardar");
        guardarMoto.addActionListener(this::guardarMotoActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(141, 141, 141)
                        .addComponent(color))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(NoMatricula)
                            .addComponent(marca, 0, 190, Short.MAX_VALUE)
                            .addComponent(Modelo)
                            .addComponent(año))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(Cancelar)
                        .addGap(30, 30, 30))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(guardarMoto)
                            .addComponent(eliminarMoto))
                        .addGap(21, 21, 21))))
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(NoMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(guardarMoto)
                        .addGap(7, 7, 7)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(eliminarMoto))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(año, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(color, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cancelar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NoMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoMatriculaActionPerformed
        
    }//GEN-LAST:event_NoMatriculaActionPerformed

    private void marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaActionPerformed
        
    }//GEN-LAST:event_marcaActionPerformed

    private void volver1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volver1ActionPerformed
        
        Menu nuevoFrame = new Menu();
    
    // Mostrar el nuevo frame
        nuevoFrame.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_volver1ActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
         limpiarCampos();
    }//GEN-LAST:event_CancelarActionPerformed

    private void eliminarMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarMotoActionPerformed
        if (idMotoSeleccionada == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una motocicleta de la tabla para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que deseas eliminar la motocicleta con ID: " + idMotoSeleccionada + "?\n(Esto puede fallar si existen órdenes asociadas)", 
            "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (motoAlmacen.eliminarMoto(idMotoSeleccionada)) {
                JOptionPane.showMessageDialog(this, "Motocicleta eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                cargarTablaMotos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la motocicleta. Podría haber registros asociados (llaves foráneas).", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_eliminarMotoActionPerformed

    private void ModeloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModeloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ModeloActionPerformed

    private void añoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_añoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_añoActionPerformed

    private void colorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_colorActionPerformed

    private void guardarMotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarMotoActionPerformed
        /// 1. Validar campos
        int idMarca = obtenerIdMarcaSeleccionada();
        if (NoMatricula.getText().isEmpty() || Modelo.getText().isEmpty() || año.getText().isEmpty() || color.getText().isEmpty() || idMarca == 0) {
            JOptionPane.showMessageDialog(this, "Todos los campos (Matrícula, Marca, Modelo, Año, Color) son obligatorios.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            int añoMoto = Integer.parseInt(año.getText());
            String nombreModelo = Modelo.getText().trim();
            
            // 2. Obtener/Crear el ID del Modelo
            int idModelo = modeloAlmacen.obtenerIdModeloPorNombre(nombreModelo, idMarca); 
            
            if (idModelo <= 0) {
                JOptionPane.showMessageDialog(this, "Error al obtener/crear el ID del Modelo.", "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Motocicleta moto = new Motocicleta();
            // Si estamos actualizando, establecemos el ID.
            if (idMotoSeleccionada > 0) {
                moto.setIdMoto(idMotoSeleccionada);
            }
            
            // Rellenar datos del objeto Motocicleta
            moto.setNoMatricula(NoMatricula.getText().trim());
            moto.setIdModelo(idModelo);
            moto.setAño(añoMoto);
            moto.setColor(color.getText().trim());
            
            // **AJUSTAR** Placeholder para ID Cliente y Número de Serie si no se capturan:
            moto.setIdCliente(1); 
            moto.setNumSerie("S/N"); 

            boolean exito;
            String mensaje;

            if (idMotoSeleccionada == 0) { // CREATE: Si no hay ID seleccionado, insertamos
                exito = motoAlmacen.insertarMoto(moto);
                mensaje = exito ? "Motocicleta guardada correctamente." : "Error al guardar la motocicleta. La matrícula podría estar duplicada.";
            } else { // UPDATE: Si hay un ID seleccionado, actualizamos
                exito = motoAlmacen.actualizarMoto(moto);
                mensaje = exito ? "Motocicleta actualizada correctamente." : "Error al actualizar la motocicleta.";
            }

            if (exito) {
                JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarCampos();
                cargarTablaMotos();
            } else {
                JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El campo Año debe ser un número entero válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException e) {
            logger.log(Level.SEVERE, "Error general al guardar/actualizar la moto: ", e);
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_guardarMotoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Moto().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancelar;
    private javax.swing.JTextField Modelo;
    private javax.swing.JTextField NoMatricula;
    private javax.swing.JTextField año;
    private javax.swing.JTextField color;
    private javax.swing.JButton eliminarMoto;
    private javax.swing.JButton guardarMoto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JComboBox<String> marca;
    private javax.swing.JTable tableMotos;
    private javax.swing.JButton volver1;
    // End of variables declaration//GEN-END:variables

    private void limpiarCampos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

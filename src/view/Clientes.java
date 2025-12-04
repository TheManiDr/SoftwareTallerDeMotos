/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import Almacen.ClienteAlmacen;
import  Model.Cliente;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author lobat
 */
public final class Clientes extends javax.swing.JFrame {
    
    private void inicializarFiltroBusqueda() {
    // 1. Configurar el RowSorter en la tabla
    trsFiltro = new TableRowSorter(modeloTabla);
    tblClientes.setRowSorter(trsFiltro);

    // 2. Agregar DocumentListener al campo de texto
    txtBuscarCliente.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            aplicarFiltro();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            aplicarFiltro();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // No se usa para JTextfields
        }
    });
}

private void aplicarFiltro() {
    try {
        String textoBusqueda = txtBuscarCliente.getText();
        
        // El filtro buscará en las columnas de la tabla (0, 1, 2, 3)
        // 0: ID | 1: Nombre | 2: Apellidos | 3: Ciudad
        // (?i) = Ignorar mayúsculas/minúsculas.
        
        // Buscamos en las columnas visibles (ID, Nombre, Apellidos, Ciudad)
        trsFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + textoBusqueda, 
                                                     1, 2, 3)); 

    } catch (java.util.regex.PatternSyntaxException e) {
        // En caso de error en la expresión de búsqueda (ej: un solo paréntesis)
        System.err.println("Error en la expresión de búsqueda: " + e.getMessage());
        trsFiltro.setRowFilter(null); 
    }
}
    private TableRowSorter trsFiltro;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Clientes.class.getName());
    
    private final ClienteAlmacen almacen = new ClienteAlmacen();
    private DefaultTableModel modeloTabla;
    
    private int idClienteSeleccionado = 0; 
    private int idDireccionSeleccionada = 0;
    
    public Clientes() {
       initComponents();
       setLocationRelativeTo(null);
    
       configurarTabla();
       cargarTablaClientes();
       inicializarFiltroBusqueda();
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        volver3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtBuscarCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtIdentificacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCalle = new javax.swing.JTextField();
        txtCiudad = new javax.swing.JTextField();
        txtCorreoElectronico = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardarActualizar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        txtEstado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setPreferredSize(new java.awt.Dimension(598, 500));

        jPanel2.setBackground(new java.awt.Color(204, 51, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(65, 66));

        volver3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        volver3.setText("<");
        volver3.addActionListener(this::volver3ActionPerformed);

        jLabel7.setText("Buscar");

        txtBuscarCliente.addActionListener(this::txtBuscarClienteActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(volver3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(volver3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Identificación");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido");

        txtIdentificacion.addActionListener(this::txtIdentificacionActionPerformed);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Domicilio");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Correo electrónico");

        txtCalle.setText("Dirección");

        txtCiudad.setText("Ciudad");

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblClientes);

        btnEliminar.setBackground(new java.awt.Color(204, 51, 0));
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnCancelar.setBackground(new java.awt.Color(204, 51, 0));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnGuardarActualizar.setBackground(new java.awt.Color(204, 51, 0));
        btnGuardarActualizar.setText("Guardar");
        btnGuardarActualizar.addActionListener(this::btnGuardarActualizarActionPerformed);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Telefono");

        txtEstado.setText("Estado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(44, 44, 44)
                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnGuardarActualizar))
                            .addComponent(txtCorreoElectronico))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCalle, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(txtTelefono))
                        .addGap(18, 18, 18)
                        .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(102, Short.MAX_VALUE))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(jLabel2)
                    .addContainerGap(579, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardarActualizar))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addComponent(jLabel2)
                    .addContainerGap(386, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void configurarTabla() {
        // Renombrando para usar nombres claros: jTableClientes
        modeloTabla = new DefaultTableModel(
            new Object[]{"ID", "Nombre", "Apellidos", "Ciudad", "ID Dir."}, 0 
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tblClientes.setModel(modeloTabla); 
        
        // Ocultar la columna idDireccion (Columna 4)
        tblClientes.getColumnModel().getColumn(4).setMinWidth(0);
        tblClientes.getColumnModel().getColumn(4).setMaxWidth(0);
        tblClientes.getColumnModel().getColumn(4).setWidth(0);
        
        // Listener para Clic en la Tabla (USANDO NOMBRE CLARO)
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                 jTableClientesMouseClicked(evt);
            }

        });
    }
    
    public void cargarTablaClientes() {
        modeloTabla.setRowCount(0); 
        List<Cliente> lista = almacen.obtenerClientes();

        for (Cliente c : lista) {
            // Solo mostrar clientes con estatus 'Activo'
            if ("Activo".equalsIgnoreCase(c.getEstatus())) { 
                modeloTabla.addRow(new Object[]{
                    c.getIdPersona(),
                    c.getNombre(),
                    c.getApellidoP() + " " + c.getApellidoM(),
                    c.getCiudad(),
                    c.getIdDireccion() 
                });
            }
        }
    }
    
    private void resetearModo() {
        idClienteSeleccionado = 0;
        idDireccionSeleccionada = 0;
        btnGuardarActualizar.setText("Guardar"); 
        limpiarCampos();
    }
    
    private void limpiarCampos() {
       txtNombre.setText("");
        txtApellido.setText("");
        // El ID debe limpiarse, aunque sea no editable
        txtIdentificacion.setText(""); 
        txtCorreoElectronico.setText(""); 
        
        txtTelefono.setText(""); // <-- Teléfono
        
        txtCalle.setText(""); 
        txtCiudad.setText("");
        txtEstado.setText("");
    }
    private void volver3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volver3ActionPerformed
        view.Menu nuevoFrame = new view.Menu();
        nuevoFrame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_volver3ActionPerformed

    private void txtIdentificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdentificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdentificacionActionPerformed

    private void btnGuardarActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActualizarActionPerformed
      Cliente cliente = new Cliente();
        boolean exito = false;

        try {
            // 1. Llenar el objeto Cliente con datos de la interfaz
            cliente.setNombre(txtNombre.getText());
            cliente.setApellidoP(txtApellido.getText());
            cliente.setApellidoM(""); // Apellido materno lo dejamos vacío por simplificación
            cliente.setCorreo(txtCorreoElectronico.getText()); 
            
            cliente.setTelefono(txtTelefono.getText()); // <-- TELEFONO

            // Dirección
            cliente.setCalle(txtCalle.getText());
            cliente.setCiudad(txtCiudad.getText());
            cliente.setEstado(txtEstado.getText()); 
            cliente.setCodigoPostal("00000"); // Asumimos un CP por defecto si no tienes campo

            // Cliente
            cliente.setTipoCliente("Normal");
            cliente.setLimiteCredito(5000.0);

            // 2. Lógica de Decisión (CREATE vs. UPDATE)
            if (idClienteSeleccionado == 0) {
                // MODO CREAR
                exito = almacen.insertarCliente(cliente);
            } else {
                // MODO ACTUALIZAR
                cliente.setIdPersona(idClienteSeleccionado);
                cliente.setIdDireccion(idDireccionSeleccionada); 
                exito = almacen.actualizarCliente(cliente); 
            }

            // 3. Post-Operación
            if (exito) {
                String mensaje = (idClienteSeleccionado == 0) ? "Cliente creado con éxito." : "Cliente actualizado con éxito.";
                JOptionPane.showMessageDialog(this, mensaje, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                
                resetearModo();
                cargarTablaClientes(); 
            } else {
                JOptionPane.showMessageDialog(this, "Error al procesar el cliente. Revise la consola del sistema.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (HeadlessException ex) {
            JOptionPane.showMessageDialog(this, "Verifique los datos ingresados: " + ex.getMessage(), "Error de Formato", JOptionPane.ERROR_MESSAGE);
            logger.log(java.util.logging.Level.SEVERE, "Error en btnGuardarActualizarActionPerformed", ex);
        }
    }//GEN-LAST:event_btnGuardarActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tblClientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para dar de baja.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idPersona = (int) modeloTabla.getValueAt(fila, 0); 
        
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de dar de BAJA (Eliminación Lógica) al cliente ID: " + idPersona + "?", 
            "Confirmar Baja", 
            JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            if (almacen.eliminarCliente(idPersona)) {
                JOptionPane.showMessageDialog(this, "Cliente dado de baja con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                resetearModo();
                cargarTablaClientes();
            } else {
                JOptionPane.showMessageDialog(this, "Error al dar de baja el cliente.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       resetearModo();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClienteActionPerformed
        
    }//GEN-LAST:event_txtBuscarClienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
      java.awt.EventQueue.invokeLater(() -> new Clientes().setVisible(true));
    }
    // Agrega este método en algún lugar de la clase Clientes.java
private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {                                             
    // Lógica para cargar los datos del cliente de la tabla a los campos de texto
    int fila = tblClientes.getSelectedRow();
    
    if (fila >= 0) {
        // 1. Obtener el ID del cliente (Columna 0 en el modeloTabla)
        int idCliente = (int) modeloTabla.getValueAt(fila, 0);
        
        // 2. Obtener el ID de la dirección (Columna 4 en el modeloTabla - la oculta)
        int idDireccion = (int) modeloTabla.getValueAt(fila, 4); 
        
        // Almacenar los IDs para la actualización/eliminación
        this.idClienteSeleccionado = idCliente;
        this.idDireccionSeleccionada = idDireccion;
        
        // Cambiar el botón a "Actualizar"
        btnGuardarActualizar.setText("Actualizar");

        // 3. Obtener el objeto Cliente completo de la base de datos usando el ID
        Cliente cliente = almacen.obtenerClientePorId(idCliente);
        
        // 4. Cargar los datos a los campos
        if (cliente != null) {
            txtIdentificacion.setText(String.valueOf(cliente.getIdPersona()));
            txtNombre.setText(cliente.getNombre());
            
            // Los apellidos están concatenados en la tabla, se necesita el Apellido Paterno por separado
            txtApellido.setText(cliente.getApellidoP()); // Asumiendo que txtApellido es solo Apellido Paterno
            
            txtTelefono.setText(cliente.getTelefono());
            txtCorreoElectronico.setText(cliente.getCorreo());
            
            // Datos de la dirección
            txtCalle.setText(cliente.getCalle());
            txtCiudad.setText(cliente.getCiudad());
            txtEstado.setText(cliente.getEstado());
        }
    }
}
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarActualizar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCorreoElectronico;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtIdentificacion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JButton volver3;
    // End of variables declaration//GEN-END:variables
}

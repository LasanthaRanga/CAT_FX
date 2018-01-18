/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.StageStyle;
import modle.Nature;
import modle.SubNature;
import modle.TradeType;
import pojo.TradeNature;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class NatureController implements Initializable {

    TradeType tradeType = new modle.TradeType();

    @FXML
    private JFXComboBox<String> com_tname;

    @FXML
    private JFXTextField txt_nno;

    @FXML
    private JFXTextField txt_nature;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private TableView<NatureTbl> tbl_nature;

    @FXML
    private TableColumn<NatureTbl, Integer> col_idNature;

    @FXML
    private TableColumn<NatureTbl, String> col_nature_no;

    @FXML
    private TableColumn<NatureTbl, String> col_nature;

    @FXML
    private TableView<SubTbl> tbl_subnature;

    @FXML
    private TableColumn<SubTbl, Integer> col_idSub;

    @FXML
    private TableColumn<SubTbl, String> col_sub_nature_no;

    @FXML
    private TableColumn<SubTbl, String> col_sub_nature;

    @FXML
    private JFXTextField txt_sno;

    @FXML
    private JFXTextField txt_subnature;

    @FXML
    private JFXButton btn_add_sub;

    @FXML
    private JFXButton btn_update_sub;

    @FXML
    private JFXButton btn_delete_sub;

    @FXML
    private StackPane stakpain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCombo();
        String select = com_tname.getSelectionModel().getSelectedItem();
        saveNature();
        getTradyType();
        updateNature();
        loadNature();
        getSelectedNature();
        upadateNature();
        deactiveNature();

        btn_add.setDisable(true);
        btn_update.setDisable(true);
        btn_delete.setDisable(true);

        btn_add_sub.setDisable(true);
        btn_update_sub.setDisable(true);
        btn_delete_sub.setDisable(true);

        saveSubNature();
        loadSubTbl();
        selectSubNature();
        updateSubNature();
        deactivSubNature();
    }

    public void loadCombo() {
        List<pojo.TradeType> loadTreadType = tradeType.loadTreadType();
        ObservableList List = FXCollections.observableArrayList();
        for (pojo.TradeType tradeType1 : loadTreadType) {
            List.add(tradeType1.getTypeName());
        }

        com_tname.setItems(List);
    }

    int tt = 0;

    public void getTradyType() {
        com_tname.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String selectedItem = com_tname.getSelectionModel().getSelectedItem();
                tt = tradeType.loadTreadType(selectedItem).getIdTradeType();
                loadNature();
                clearNature();
                btn_add.setDisable(false);
                idNature = 0;
                loadSubTbl();
            }
        });

    }

    public void saveNature() {
        btn_add.setOnAction((ActionEvent event) -> {
            // System.out.println("click");
            pojo.TradeType loadTreadType = tradeType.loadTreadType(tt);

            if (loadTreadType != null) {
                //  System.out.println("not null");
                Nature nature = new modle.Nature();
                nature.setTradeType(loadTreadType);
                nature.setNatureNo(txt_nno.getText());
                nature.setNature(txt_nature.getText());
                nature.saveNature();
                messagSuccsess();
                loadNature();
            }

        });
    }

    public void updateNature() {
        btn_update.setOnAction((event) -> {
            modle.Allert.normalMg();

        });
    }

    public void messagSuccsess() {
        System.out.println("ELA ELA");
        JFXDialogLayout content = new JFXDialogLayout();
        Text text = new Text("Succes");
        content.setHeading(text);
        content.setBackground(new Background(new BackgroundFill(Paint.valueOf("#4CAF50"), CornerRadii.EMPTY, Insets.EMPTY)));

        //content.setBody(new Text("Thank You"));
        JFXButton button = new JFXButton("OK");
        button.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FAFAFA"), CornerRadii.EMPTY, Insets.EMPTY)));
        JFXDialog jfxDialog = new JFXDialog(stakpain, content, JFXDialog.DialogTransition.RIGHT);
        button.setOnAction((e) -> {
            jfxDialog.close();
        });
        content.setActions(button);
        jfxDialog.show();
    }

    //==================================================
    ObservableList natureList = FXCollections.observableArrayList();

    public class NatureTbl {

        /**
         * @return the nid
         */
        public int getNid() {
            return nid;
        }

        /**
         * @return the nname
         */
        public String getNname() {
            return nname.get();
        }

        /**
         * @return the nno
         */
        public String getNno() {
            return nno.get();
        }

        private int nid;
        private SimpleStringProperty nname;
        private SimpleStringProperty nno;

        public NatureTbl(int nid, String nname, String nno) {
            this.nid = nid;
            this.nname = new SimpleStringProperty(nname);
            this.nno = new SimpleStringProperty(nno);
        }
    }

    public void loadNature() {

        col_idNature.setCellValueFactory(new PropertyValueFactory<>("nid"));
        col_nature.setCellValueFactory(new PropertyValueFactory<>("nname"));
        col_nature_no.setCellValueFactory(new PropertyValueFactory<>("nno"));

        Nature tn = new modle.Nature();
        tn.setTradeType(new modle.TradeType().loadTreadType(tt));
        List<TradeNature> nlist = tn.getNaturesList();
        natureList.clear();
        for (pojo.TradeNature tnn : nlist) {
            natureList.add(new NatureTbl(tnn.getIdTradeNature(), tnn.getNature(), tnn.getNatureNo()));
        }
        tbl_nature.setItems(natureList);

    }

    int idNature = 0;

    public void getSelectedNature() {
        tbl_nature.setOnMouseReleased((MouseEvent event) -> {
            int nid = tbl_nature.getSelectionModel().getSelectedItem().getNid();
            String nname = tbl_nature.getSelectionModel().getSelectedItem().getNname();
            String nno = tbl_nature.getSelectionModel().getSelectedItem().getNno();
            txt_nno.setText(nno);
            txt_nature.setText(nname);
            idNature = nid;
            ennableNature();
            loadSubTbl();
            txt_sno.setText(null);
            txt_subnature.setText(null);
            btn_update_sub.setDisable(true);
            btn_delete_sub.setDisable(true);
        });

    }

    public void clearNature() {
        txt_nno.setText(null);
        txt_nature.setText(null);
        btn_update.setDisable(true);
        btn_delete.setDisable(true);
        btn_add_sub.setDisable(true);
        btn_update_sub.setDisable(true);
        btn_delete_sub.setDisable(true);
    }

    public void ennableNature() {
        btn_update.setDisable(false);
        btn_delete.setDisable(false);
        btn_add_sub.setDisable(false);
    }

    public void cleareSubNatue() {
        btn_add_sub.setDisable(true);
        btn_update_sub.setDisable(true);
        btn_delete_sub.setDisable(true);
        txt_subnature.setText(null);
        txt_sno.setText(null);
    }

    public void ennableSubNatue() {
        btn_add_sub.setDisable(false);
        btn_update_sub.setDisable(false);
        btn_delete_sub.setDisable(false);

    }

    public void upadateNature() {
        btn_update.setOnAction((ActionEvent event) -> {
            Nature nature = new modle.Nature();
            nature.setIdTradeNature(idNature);
            nature.setNatureNo(txt_nno.getText());
            nature.setNature(txt_nature.getText());
            nature.updateNature();
            loadNature();
            clearNature();
            messagSuccsess();
        });
    }

    public void deactiveNature() {
        btn_delete.setOnAction((event) -> {
            Nature nature = new modle.Nature();
            nature.setIdTradeNature(idNature);
//            nature.setNatureNo(txt_nno.getText());
//            nature.setNature(txt_nature.getText());
            nature.deactiveNature();
            loadNature();
            clearNature();
            messagSuccsess();

        });
    }

    public void saveSubNature() {
        btn_add_sub.setOnAction((ActionEvent event) -> {
            SubNature subNature = new modle.SubNature(0, new modle.Nature().getNatureById(idNature), txt_sno.getText(), txt_subnature.getText(), 1, 1);
            subNature.saveNature();
            messagSuccsess();
            cleareSubNatue();
            loadSubTbl();
        });

    }

    ObservableList subList = FXCollections.observableArrayList();

    public class SubTbl {

        /**
         * @return the sid
         */
        public int getSid() {
            return sid;
        }

        /**
         * @return the sname
         */
        public String getSname() {
            return sname.get();
        }

        /**
         * @return the sno
         */
        public String getSno() {
            return sno.get();
        }

        private final int sid;
        private final SimpleStringProperty sname;
        private final SimpleStringProperty sno;

        public SubTbl(int sid, String sname, String sno) {
            this.sid = sid;
            this.sname = new SimpleStringProperty(sname);
            this.sno = new SimpleStringProperty(sno);
        }
    }

    public void loadSubTbl() {

        col_idSub.setCellValueFactory(new PropertyValueFactory<>("sid"));
        col_sub_nature.setCellValueFactory(new PropertyValueFactory<>("sname"));
        col_sub_nature_no.setCellValueFactory(new PropertyValueFactory<>("sno"));

        modle.SubNature sn = new modle.SubNature(0, new modle.Nature().getNatureById(idNature), null, null, 0, 0);
        List<pojo.SubNature> loadNature = sn.loadNature();

        subList.clear();
        for (pojo.SubNature tnn : loadNature) {
            subList.add(new SubTbl(tnn.getIdSubNature(), tnn.getSubNature(), tnn.getSubNatureNo()));
        }
        tbl_subnature.setItems(subList);

    }

    int idSubnatue = 0;

    public void selectSubNature() {
        tbl_subnature.setOnMouseReleased((event) -> {
            int sid = tbl_subnature.getSelectionModel().getSelectedItem().getSid();
            String sno = tbl_subnature.getSelectionModel().getSelectedItem().getSno();
            String sname = tbl_subnature.getSelectionModel().getSelectedItem().getSname();
            idSubnatue = sid;
            txt_sno.setText(sno);
            txt_subnature.setText(sname);
            ennableSubNatue();
        });

    }

    public void updateSubNature() {

        btn_update_sub.setOnAction((event) -> {

            SubNature subNature = new modle.SubNature(idSubnatue, null, txt_sno.getText(), txt_subnature.getText(), 1, 1);
            subNature.updateNature();
            modle.Allert.messagSuccsess(stakpain, "Updated", "sub nature");
            loadSubTbl();
            txt_sno.setText(null);
            txt_subnature.setText(null);
            btn_update_sub.setDisable(true);
            btn_delete_sub.setDisable(true);

        });

    }

    public void deactivSubNature() {
        btn_delete_sub.setOnAction((event) -> {
            SubNature subNature = new modle.SubNature(idSubnatue, null, null, null, 0, 1);
            subNature.deactiveNature();
            modle.Allert.messagSuccsess(stakpain, "Deactiveted", "sub nature");
            loadSubTbl();
            txt_sno.setText(null);
            txt_subnature.setText(null);
            btn_update_sub.setDisable(true);
            btn_delete_sub.setDisable(true);

        });
    }

}

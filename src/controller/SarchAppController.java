/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modle.AppHolder;
import modle.AppTbl;
import modle.ApprvalHolder;
import modle.Export_TO_Excel;
import modle.LoadAppList;
import modle.Nature;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import pojo.Application;
import pojo.TradeNature;
import pojo.Ward;

/**
 * FXML Controller class
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class SarchAppController implements Initializable {

    @FXML
    private JFXCheckBox ch_appno;
    @FXML
    private JFXTextField txt_appno;
    @FXML
    private JFXCheckBox ch_street;
    @FXML
    private JFXComboBox<String> com_street;
    @FXML
    private JFXCheckBox ch_ward;
    @FXML
    private JFXComboBox<String> com_ward;
    @FXML
    private JFXCheckBox ch_trade_nature;
    @FXML
    private JFXComboBox<String> com_trade_nature;
    @FXML
    private JFXCheckBox ch_trade_type;
    @FXML
    private JFXComboBox<String> com_trade_type;
    @FXML
    private JFXCheckBox ch_assesmant;
    @FXML
    private JFXTextField txt_assesmant;
    @FXML
    private JFXCheckBox ch_Authoritist;
    @FXML
    private JFXComboBox<String> com_authoritist;
    @FXML
    private JFXRadioButton ra_onebycat;
    @FXML
    private ToggleGroup Acat;
    @FXML
    private JFXRadioButton ra_allcat;
    @FXML
    private JFXRadioButton ra_status_all;
    @FXML
    private ToggleGroup approveStatus;
    @FXML
    private JFXRadioButton ra_status_approve;
    @FXML
    private JFXRadioButton ra_status_none;
    @FXML
    private JFXRadioButton ra_status_pending;
    @FXML
    private JFXCheckBox ch_robill;
    @FXML
    private JFXComboBox<String> com_ronames;
    @FXML
    private JFXRadioButton ra_byroname;
    @FXML
    private JFXRadioButton ra_allRo;
    @FXML
    private ToggleGroup ro;
    @FXML
    private JFXCheckBox ch_pay;
    @FXML
    private JFXRadioButton ra_payApprove;
    @FXML
    private ToggleGroup pay;
    @FXML
    private JFXRadioButton ra_payNoneApprove;
    @FXML
    private JFXRadioButton ra_paid;
    @FXML
    private JFXCheckBox ch_date;
    @FXML
    private JFXDatePicker date_form;
    @FXML
    private JFXDatePicker date_to;

    @FXML
    private TableView<AppTbl> tbl_applicaion;
    @FXML
    private TableColumn<AppTbl, String> c_idApp;
    @FXML
    private TableColumn<AppTbl, String> c_type;
    @FXML
    private TableColumn<AppTbl, String> c_nature;
    @FXML
    private TableColumn<AppTbl, Double> c_alocation;
    @FXML
    private TableColumn<AppTbl, Double> c_tax;
    @FXML
    private TableColumn<AppTbl, Integer> c_approve;
    @FXML
    private TableColumn<AppTbl, String> c_tname;
    @FXML
    private JFXButton btn_All;
    private JFXButton btn_paymant_report;
    @FXML
    private JFXButton btn_export;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    String appNO;
    String assessNo;
    String tradeType;
    String tradeNature;
    String ward;
    String street;
    String authoritist;

    boolean hassCat;
    Integer outhorityStatus;

    boolean hasRO;
    String roname;

    boolean hasPay;
    Integer payStatus;

    boolean hasDate;
    Date fromDate;
    Date toDate;

    HashSet<AppHolder> loadAllAppList;
    HashSet<AppHolder> filter = new HashSet<>();

    public void searchAppList() {

        filter.clear();
        for (AppHolder appHolder : loadAllAppList) {

            if (appNO != null) {
                if (appHolder.getApplicationNo().equals(appNO)) {
                    filter.add(appHolder);
                }
            } else {
                if (hassCat) {
                    if (authoritist != null) {
                        HashSet<ApprvalHolder> approveList = appHolder.getApproveList();
                        if (approveList != null && approveList.size() > 0) {
                            for (ApprvalHolder ah : approveList) {
                                String otheritisCat = ah.getOtheritisCat();
                                if (authoritist.equals(otheritisCat)) {
                                    Integer status = ah.getStatus();
                                    if (outhorityStatus == status) {
                                        //pending
                                        if (hasDate) {
                                            Date paymentDate = appHolder.getPaymentDate();
                                            Date from = Date.from(date_form.getValue().atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant());
                                            Date to = Date.from(date_to.getValue().atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant());

                                            if (paymentDate != null) {
                                                if (paymentDate.compareTo(from) >= 0 && paymentDate.compareTo(to) <= 0) {
//pAYMANT dATE
                                                    if (hasPay) {
                                                        Integer approveToPaymant = appHolder.getApproveToPaymant();
                                                        if (approveToPaymant == payStatus) {
                                                            if (hasRO) {
                                                                if (roname != null) {
                                                                    if (roname.equals(appHolder.getRoName())) {
                                                                        if (tradeNature != null) {
                                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                                if (assessNo != null) {
                                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (street != null) {
                                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (ward != null) {
                                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else {
                                                                                    filter.add(appHolder);
                                                                                }

                                                                            }
                                                                        } else if (tradeType != null) {
                                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                                if (assessNo != null) {
                                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (street != null) {
                                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (ward != null) {
                                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    }
                                                                } else {
                                                                    //RO name Nethuwa Okkoma RO lage
                                                                    if (appHolder.getRoName() != null) {
                                                                        if (tradeNature != null) {
                                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                                if (assessNo != null) {
                                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (street != null) {
                                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (ward != null) {
                                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            }
                                                                        } else if (tradeType != null) {
                                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                                if (assessNo != null) {
                                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (street != null) {
                                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (ward != null) {
                                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            } else {

                                                                if (tradeNature != null) {
                                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }

                                                                    }
                                                                } else if (tradeType != null) {
                                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    } else {
                                                        if (hasRO) {
                                                            if (roname != null) {
                                                                if (roname.equals(appHolder.getRoName())) {
                                                                    if (tradeNature != null) {
                                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }

                                                                        }
                                                                    } else if (tradeType != null) {
                                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                //RO name Nethuwa Okkoma RO lage
                                                                if (appHolder.getRoName() != null) {
                                                                    if (tradeNature != null) {
                                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else if (tradeType != null) {
                                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } else {

                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }

                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }

                                                        }
                                                    }

                                                }
                                            }

                                        } else {

                                            if (hasPay) {
                                                Integer approveToPaymant = appHolder.getApproveToPaymant();
                                                if (approveToPaymant == payStatus) {
                                                    if (hasRO) {
                                                        if (roname != null) {
                                                            if (roname.equals(appHolder.getRoName())) {
                                                                if (tradeNature != null) {
                                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }

                                                                    }
                                                                } else if (tradeType != null) {
                                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            //RO name Nethuwa Okkoma RO lage
                                                            if (appHolder.getRoName() != null) {
                                                                if (tradeNature != null) {
                                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else if (tradeType != null) {
                                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {

                                                        if (tradeNature != null) {
                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }

                                                            }
                                                        } else if (tradeType != null) {
                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        } else {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }

                                                    }
                                                }
                                            } else {
                                                if (hasRO) {
                                                    if (roname != null) {
                                                        if (roname.equals(appHolder.getRoName())) {
                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }

                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        //RO name Nethuwa Okkoma RO lage
                                                        if (appHolder.getRoName() != null) {
                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {

                                                    if (tradeNature != null) {
                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }

                                                        }
                                                    } else if (tradeType != null) {
                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    } else {
                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }

                                                }
                                            }

                                        }
                                    } else if (outhorityStatus == 3) {
                                        //all
                                        if (hasDate) {
                                            Date paymentDate = appHolder.getPaymentDate();
                                            Date from = Date.from(date_form.getValue().atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant());
                                            Date to = Date.from(date_to.getValue().atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant());

                                            if (paymentDate != null) {
                                                if (paymentDate.compareTo(from) >= 0 && paymentDate.compareTo(to) <= 0) {
//pAYMANT dATE
                                                    if (hasPay) {
                                                        Integer approveToPaymant = appHolder.getApproveToPaymant();
                                                        if (approveToPaymant == payStatus) {
                                                            if (hasRO) {
                                                                if (roname != null) {
                                                                    if (roname.equals(appHolder.getRoName())) {
                                                                        if (tradeNature != null) {
                                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                                if (assessNo != null) {
                                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (street != null) {
                                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (ward != null) {
                                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else {
                                                                                    filter.add(appHolder);
                                                                                }

                                                                            }
                                                                        } else if (tradeType != null) {
                                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                                if (assessNo != null) {
                                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (street != null) {
                                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (ward != null) {
                                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    }
                                                                } else {
                                                                    //RO name Nethuwa Okkoma RO lage
                                                                    if (appHolder.getRoName() != null) {
                                                                        if (tradeNature != null) {
                                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                                if (assessNo != null) {
                                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (street != null) {
                                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (ward != null) {
                                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            }
                                                                        } else if (tradeType != null) {
                                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                                if (assessNo != null) {
                                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (street != null) {
                                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else if (ward != null) {
                                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                                        filter.add(appHolder);
                                                                                    }
                                                                                } else {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            } else {

                                                                if (tradeNature != null) {
                                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }

                                                                    }
                                                                } else if (tradeType != null) {
                                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    } else {
                                                        if (hasRO) {
                                                            if (roname != null) {
                                                                if (roname.equals(appHolder.getRoName())) {
                                                                    if (tradeNature != null) {
                                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }

                                                                        }
                                                                    } else if (tradeType != null) {
                                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                //RO name Nethuwa Okkoma RO lage
                                                                if (appHolder.getRoName() != null) {
                                                                    if (tradeNature != null) {
                                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else if (tradeType != null) {
                                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } else {

                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }

                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }

                                                        }
                                                    }

                                                }
                                            }

                                        } else {

                                            if (hasPay) {
                                                Integer approveToPaymant = appHolder.getApproveToPaymant();
                                                if (approveToPaymant == payStatus) {
                                                    if (hasRO) {
                                                        if (roname != null) {
                                                            if (roname.equals(appHolder.getRoName())) {
                                                                if (tradeNature != null) {
                                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }

                                                                    }
                                                                } else if (tradeType != null) {
                                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            //RO name Nethuwa Okkoma RO lage
                                                            if (appHolder.getRoName() != null) {
                                                                if (tradeNature != null) {
                                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else if (tradeType != null) {
                                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {

                                                        if (tradeNature != null) {
                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }

                                                            }
                                                        } else if (tradeType != null) {
                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        } else {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }

                                                    }
                                                }
                                            } else {
                                                if (hasRO) {
                                                    if (roname != null) {
                                                        if (roname.equals(appHolder.getRoName())) {
                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }

                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        //RO name Nethuwa Okkoma RO lage
                                                        if (appHolder.getRoName() != null) {
                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {

                                                    if (tradeNature != null) {
                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }

                                                        }
                                                    } else if (tradeType != null) {
                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    } else {
                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }

                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        HashSet<ApprvalHolder> approveList = appHolder.getApproveList();
                        if (approveList != null && approveList.size() > 0) {
                            for (ApprvalHolder ah : approveList) {
                                String otheritisCat = ah.getOtheritisCat();
                                Integer status = ah.getStatus();
                                if (outhorityStatus == status) {
                                    //pending
                                    if (hasDate) {
                                        Date paymentDate = appHolder.getPaymentDate();
                                        Date from = Date.from(date_form.getValue().atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant());
                                        Date to = Date.from(date_to.getValue().atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant());

                                        if (paymentDate != null) {
                                            if (paymentDate.compareTo(from) >= 0 && paymentDate.compareTo(to) <= 0) {
//pAYMANT dATE
                                                if (hasPay) {
                                                    Integer approveToPaymant = appHolder.getApproveToPaymant();
                                                    if (approveToPaymant == payStatus) {
                                                        if (hasRO) {
                                                            if (roname != null) {
                                                                if (roname.equals(appHolder.getRoName())) {
                                                                    if (tradeNature != null) {
                                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }

                                                                        }
                                                                    } else if (tradeType != null) {
                                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                //RO name Nethuwa Okkoma RO lage
                                                                if (appHolder.getRoName() != null) {
                                                                    if (tradeNature != null) {
                                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else if (tradeType != null) {
                                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } else {

                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }

                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }

                                                        }
                                                    }
                                                } else {
                                                    if (hasRO) {
                                                        if (roname != null) {
                                                            if (roname.equals(appHolder.getRoName())) {
                                                                if (tradeNature != null) {
                                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }

                                                                    }
                                                                } else if (tradeType != null) {
                                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            //RO name Nethuwa Okkoma RO lage
                                                            if (appHolder.getRoName() != null) {
                                                                if (tradeNature != null) {
                                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else if (tradeType != null) {
                                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {

                                                        if (tradeNature != null) {
                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }

                                                            }
                                                        } else if (tradeType != null) {
                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        } else {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }

                                                    }
                                                }

                                            }
                                        }

                                    } else {

                                        if (hasPay) {
                                            Integer approveToPaymant = appHolder.getApproveToPaymant();
                                            if (approveToPaymant == payStatus) {
                                                if (hasRO) {
                                                    if (roname != null) {
                                                        if (roname.equals(appHolder.getRoName())) {
                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }

                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        //RO name Nethuwa Okkoma RO lage
                                                        if (appHolder.getRoName() != null) {
                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {

                                                    if (tradeNature != null) {
                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }

                                                        }
                                                    } else if (tradeType != null) {
                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    } else {
                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }

                                                }
                                            }
                                        } else {
                                            if (hasRO) {
                                                if (roname != null) {
                                                    if (roname.equals(appHolder.getRoName())) {
                                                        if (tradeNature != null) {
                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }

                                                            }
                                                        } else if (tradeType != null) {
                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        } else {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    //RO name Nethuwa Okkoma RO lage
                                                    if (appHolder.getRoName() != null) {
                                                        if (tradeNature != null) {
                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        } else if (tradeType != null) {
                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        } else {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {

                                                if (tradeNature != null) {
                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }

                                                    }
                                                } else if (tradeType != null) {
                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }
                                                } else {
                                                    if (assessNo != null) {
                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (street != null) {
                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (ward != null) {
                                                        if (ward.equals(appHolder.getWardName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else {
                                                        filter.add(appHolder);
                                                    }
                                                }

                                            }
                                        }

                                    }
                                } else if (outhorityStatus == 3) {
                                    //all
                                    if (hasDate) {
                                        Date paymentDate = appHolder.getPaymentDate();
                                        Date from = Date.from(date_form.getValue().atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant());
                                        Date to = Date.from(date_to.getValue().atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant());

                                        if (paymentDate != null) {
                                            if (paymentDate.compareTo(from) >= 0 && paymentDate.compareTo(to) <= 0) {
//pAYMANT dATE
                                                if (hasPay) {
                                                    Integer approveToPaymant = appHolder.getApproveToPaymant();
                                                    if (approveToPaymant == payStatus) {
                                                        if (hasRO) {
                                                            if (roname != null) {
                                                                if (roname.equals(appHolder.getRoName())) {
                                                                    if (tradeNature != null) {
                                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }

                                                                        }
                                                                    } else if (tradeType != null) {
                                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                }
                                                            } else {
                                                                //RO name Nethuwa Okkoma RO lage
                                                                if (appHolder.getRoName() != null) {
                                                                    if (tradeNature != null) {
                                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else if (tradeType != null) {
                                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                            if (assessNo != null) {
                                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (street != null) {
                                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else if (ward != null) {
                                                                                if (ward.equals(appHolder.getWardName())) {
                                                                                    filter.add(appHolder);
                                                                                }
                                                                            } else {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        }
                                                                    } else {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        } else {

                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }

                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }

                                                        }
                                                    }
                                                } else {
                                                    if (hasRO) {
                                                        if (roname != null) {
                                                            if (roname.equals(appHolder.getRoName())) {
                                                                if (tradeNature != null) {
                                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }

                                                                    }
                                                                } else if (tradeType != null) {
                                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            //RO name Nethuwa Okkoma RO lage
                                                            if (appHolder.getRoName() != null) {
                                                                if (tradeNature != null) {
                                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else if (tradeType != null) {
                                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                        if (assessNo != null) {
                                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (street != null) {
                                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else if (ward != null) {
                                                                            if (ward.equals(appHolder.getWardName())) {
                                                                                filter.add(appHolder);
                                                                            }
                                                                        } else {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    } else {

                                                        if (tradeNature != null) {
                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }

                                                            }
                                                        } else if (tradeType != null) {
                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        } else {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }

                                                    }
                                                }

                                            }
                                        }

                                    } else {

                                        if (hasPay) {
                                            Integer approveToPaymant = appHolder.getApproveToPaymant();
                                            if (approveToPaymant == payStatus) {
                                                if (hasRO) {
                                                    if (roname != null) {
                                                        if (roname.equals(appHolder.getRoName())) {
                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }

                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        //RO name Nethuwa Okkoma RO lage
                                                        if (appHolder.getRoName() != null) {
                                                            if (tradeNature != null) {
                                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else if (tradeType != null) {
                                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                    if (assessNo != null) {
                                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (street != null) {
                                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else if (ward != null) {
                                                                        if (ward.equals(appHolder.getWardName())) {
                                                                            filter.add(appHolder);
                                                                        }
                                                                    } else {
                                                                        filter.add(appHolder);
                                                                    }
                                                                }
                                                            } else {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        }
                                                    }
                                                } else {

                                                    if (tradeNature != null) {
                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }

                                                        }
                                                    } else if (tradeType != null) {
                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    } else {
                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }

                                                }
                                            }
                                        } else {
                                            if (hasRO) {
                                                if (roname != null) {
                                                    if (roname.equals(appHolder.getRoName())) {
                                                        if (tradeNature != null) {
                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }

                                                            }
                                                        } else if (tradeType != null) {
                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        } else {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    //RO name Nethuwa Okkoma RO lage
                                                    if (appHolder.getRoName() != null) {
                                                        if (tradeNature != null) {
                                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        } else if (tradeType != null) {
                                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                                if (assessNo != null) {
                                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (street != null) {
                                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else if (ward != null) {
                                                                    if (ward.equals(appHolder.getWardName())) {
                                                                        filter.add(appHolder);
                                                                    }
                                                                } else {
                                                                    filter.add(appHolder);
                                                                }
                                                            }
                                                        } else {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    }
                                                }
                                            } else {

                                                if (tradeNature != null) {
                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }

                                                    }
                                                } else if (tradeType != null) {
                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }
                                                } else {
                                                    if (assessNo != null) {
                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (street != null) {
                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (ward != null) {
                                                        if (ward.equals(appHolder.getWardName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else {
                                                        filter.add(appHolder);
                                                    }
                                                }

                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (hasDate) {
                        Date paymentDate = appHolder.getPaymentDate();
                        Date from = Date.from(date_form.getValue().atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant());
                        Date to = Date.from(date_to.getValue().atStartOfDay().atZone(ZoneId.of("Asia/Colombo")).toInstant());

                        if (paymentDate != null) {
                            if (paymentDate.compareTo(from) >= 0 && paymentDate.compareTo(to) <= 0) {
//pAYMANT dATE
                                if (hasPay) {
                                    Integer approveToPaymant = appHolder.getApproveToPaymant();
                                    if (approveToPaymant == payStatus) {
                                        if (hasRO) {
                                            if (roname != null) {
                                                if (roname.equals(appHolder.getRoName())) {
                                                    if (tradeNature != null) {
                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }

                                                        }
                                                    } else if (tradeType != null) {
                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    } else {
                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }
                                                }
                                            } else {
                                                //RO name Nethuwa Okkoma RO lage
                                                if (appHolder.getRoName() != null) {
                                                    if (tradeNature != null) {
                                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    } else if (tradeType != null) {
                                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                            if (assessNo != null) {
                                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (street != null) {
                                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else if (ward != null) {
                                                                if (ward.equals(appHolder.getWardName())) {
                                                                    filter.add(appHolder);
                                                                }
                                                            } else {
                                                                filter.add(appHolder);
                                                            }
                                                        }
                                                    } else {
                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }
                                                }
                                            }
                                        } else {

                                            if (tradeNature != null) {
                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                    if (assessNo != null) {
                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (street != null) {
                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (ward != null) {
                                                        if (ward.equals(appHolder.getWardName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else {
                                                        filter.add(appHolder);
                                                    }

                                                }
                                            } else if (tradeType != null) {
                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                    if (assessNo != null) {
                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (street != null) {
                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (ward != null) {
                                                        if (ward.equals(appHolder.getWardName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else {
                                                        filter.add(appHolder);
                                                    }
                                                }
                                            } else {
                                                if (assessNo != null) {
                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (street != null) {
                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (ward != null) {
                                                    if (ward.equals(appHolder.getWardName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else {
                                                    filter.add(appHolder);
                                                }
                                            }

                                        }
                                    }
                                } else {
                                    if (hasRO) {
                                        if (roname != null) {
                                            if (roname.equals(appHolder.getRoName())) {
                                                if (tradeNature != null) {
                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }

                                                    }
                                                } else if (tradeType != null) {
                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }
                                                } else {
                                                    if (assessNo != null) {
                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (street != null) {
                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (ward != null) {
                                                        if (ward.equals(appHolder.getWardName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else {
                                                        filter.add(appHolder);
                                                    }
                                                }
                                            }
                                        } else {
                                            //RO name Nethuwa Okkoma RO lage
                                            if (appHolder.getRoName() != null) {
                                                if (tradeNature != null) {
                                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }
                                                } else if (tradeType != null) {
                                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                        if (assessNo != null) {
                                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (street != null) {
                                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else if (ward != null) {
                                                            if (ward.equals(appHolder.getWardName())) {
                                                                filter.add(appHolder);
                                                            }
                                                        } else {
                                                            filter.add(appHolder);
                                                        }
                                                    }
                                                } else {
                                                    if (assessNo != null) {
                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (street != null) {
                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (ward != null) {
                                                        if (ward.equals(appHolder.getWardName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else {
                                                        filter.add(appHolder);
                                                    }
                                                }
                                            }
                                        }
                                    } else {

                                        if (tradeNature != null) {
                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                if (assessNo != null) {
                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (street != null) {
                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (ward != null) {
                                                    if (ward.equals(appHolder.getWardName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else {
                                                    filter.add(appHolder);
                                                }

                                            }
                                        } else if (tradeType != null) {
                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                if (assessNo != null) {
                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (street != null) {
                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (ward != null) {
                                                    if (ward.equals(appHolder.getWardName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else {
                                                    filter.add(appHolder);
                                                }
                                            }
                                        } else {
                                            if (assessNo != null) {
                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else if (street != null) {
                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else if (ward != null) {
                                                if (ward.equals(appHolder.getWardName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else {
                                                filter.add(appHolder);
                                            }
                                        }

                                    }
                                }

                            }
                        }

                    } else {

                        if (hasPay) {
                            Integer approveToPaymant = appHolder.getApproveToPaymant();
                            if (approveToPaymant == payStatus) {
                                if (hasRO) {
                                    if (roname != null) {
                                        if (roname.equals(appHolder.getRoName())) {
                                            if (tradeNature != null) {
                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                    if (assessNo != null) {
                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (street != null) {
                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (ward != null) {
                                                        if (ward.equals(appHolder.getWardName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else {
                                                        filter.add(appHolder);
                                                    }

                                                }
                                            } else if (tradeType != null) {
                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                    if (assessNo != null) {
                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (street != null) {
                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (ward != null) {
                                                        if (ward.equals(appHolder.getWardName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else {
                                                        filter.add(appHolder);
                                                    }
                                                }
                                            } else {
                                                if (assessNo != null) {
                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (street != null) {
                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (ward != null) {
                                                    if (ward.equals(appHolder.getWardName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else {
                                                    filter.add(appHolder);
                                                }
                                            }
                                        }
                                    } else {
                                        //RO name Nethuwa Okkoma RO lage
                                        if (appHolder.getRoName() != null) {
                                            if (tradeNature != null) {
                                                if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                    if (assessNo != null) {
                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (street != null) {
                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (ward != null) {
                                                        if (ward.equals(appHolder.getWardName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else {
                                                        filter.add(appHolder);
                                                    }
                                                }
                                            } else if (tradeType != null) {
                                                if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                    if (assessNo != null) {
                                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (street != null) {
                                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else if (ward != null) {
                                                        if (ward.equals(appHolder.getWardName())) {
                                                            filter.add(appHolder);
                                                        }
                                                    } else {
                                                        filter.add(appHolder);
                                                    }
                                                }
                                            } else {
                                                if (assessNo != null) {
                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (street != null) {
                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (ward != null) {
                                                    if (ward.equals(appHolder.getWardName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else {
                                                    filter.add(appHolder);
                                                }
                                            }
                                        }
                                    }
                                } else {

                                    if (tradeNature != null) {
                                        if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                            if (assessNo != null) {
                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else if (street != null) {
                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else if (ward != null) {
                                                if (ward.equals(appHolder.getWardName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else {
                                                filter.add(appHolder);
                                            }

                                        }
                                    } else if (tradeType != null) {
                                        if (tradeType.equals(appHolder.getTradeTypeName())) {
                                            if (assessNo != null) {
                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else if (street != null) {
                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else if (ward != null) {
                                                if (ward.equals(appHolder.getWardName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else {
                                                filter.add(appHolder);
                                            }
                                        }
                                    } else {
                                        if (assessNo != null) {
                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                filter.add(appHolder);
                                            }
                                        } else if (street != null) {
                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                filter.add(appHolder);
                                            }
                                        } else if (ward != null) {
                                            if (ward.equals(appHolder.getWardName())) {
                                                filter.add(appHolder);
                                            }
                                        } else {
                                            filter.add(appHolder);
                                        }
                                    }

                                }
                            }
                        } else {
                            if (hasRO) {
                                if (roname != null) {
                                    if (roname.equals(appHolder.getRoName())) {
                                        if (tradeNature != null) {
                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                if (assessNo != null) {
                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (street != null) {
                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (ward != null) {
                                                    if (ward.equals(appHolder.getWardName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else {
                                                    filter.add(appHolder);
                                                }

                                            }
                                        } else if (tradeType != null) {
                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                if (assessNo != null) {
                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (street != null) {
                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (ward != null) {
                                                    if (ward.equals(appHolder.getWardName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else {
                                                    filter.add(appHolder);
                                                }
                                            }
                                        } else {
                                            if (assessNo != null) {
                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else if (street != null) {
                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else if (ward != null) {
                                                if (ward.equals(appHolder.getWardName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else {
                                                filter.add(appHolder);
                                            }
                                        }
                                    }
                                } else {
                                    //RO name Nethuwa Okkoma RO lage
                                    if (appHolder.getRoName() != null) {
                                        if (tradeNature != null) {
                                            if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                                if (assessNo != null) {
                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (street != null) {
                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (ward != null) {
                                                    if (ward.equals(appHolder.getWardName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else {
                                                    filter.add(appHolder);
                                                }
                                            }
                                        } else if (tradeType != null) {
                                            if (tradeType.equals(appHolder.getTradeTypeName())) {
                                                if (assessNo != null) {
                                                    if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (street != null) {
                                                    if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else if (ward != null) {
                                                    if (ward.equals(appHolder.getWardName())) {
                                                        filter.add(appHolder);
                                                    }
                                                } else {
                                                    filter.add(appHolder);
                                                }
                                            }
                                        } else {
                                            if (assessNo != null) {
                                                if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else if (street != null) {
                                                if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else if (ward != null) {
                                                if (ward.equals(appHolder.getWardName())) {
                                                    filter.add(appHolder);
                                                }
                                            } else {
                                                filter.add(appHolder);
                                            }
                                        }
                                    }
                                }
                            } else {

                                if (tradeNature != null) {
                                    if (tradeNature.equals(appHolder.getNature()) && tradeType.equals(appHolder.getTradeTypeName())) {

                                        if (assessNo != null) {
                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                filter.add(appHolder);
                                            }
                                        } else if (street != null) {
                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                filter.add(appHolder);
                                            }
                                        } else if (ward != null) {
                                            if (ward.equals(appHolder.getWardName())) {
                                                filter.add(appHolder);
                                            }
                                        } else {
                                            filter.add(appHolder);
                                        }

                                    }
                                } else if (tradeType != null) {
                                    if (tradeType.equals(appHolder.getTradeTypeName())) {
                                        if (assessNo != null) {
                                            if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                filter.add(appHolder);
                                            }
                                        } else if (street != null) {
                                            if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                                filter.add(appHolder);
                                            }
                                        } else if (ward != null) {
                                            if (ward.equals(appHolder.getWardName())) {
                                                filter.add(appHolder);
                                            }
                                        } else {
                                            filter.add(appHolder);
                                        }
                                    }
                                } else {
                                    if (assessNo != null) {
                                        if (assessNo.equals(appHolder.getAssessmantNO()) && ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                            filter.add(appHolder);
                                        }
                                    } else if (street != null) {
                                        if (ward.equals(appHolder.getWardName()) && street.equals(appHolder.getStreetName())) {
                                            filter.add(appHolder);
                                        }
                                    } else if (ward != null) {
                                        if (ward.equals(appHolder.getWardName())) {
                                            filter.add(appHolder);
                                        }
                                    } else {
                                        filter.add(appHolder);
                                    }
                                }

                            }
                        }

                    }
                }
            }

        }
        loadTable();

    }

    public void getData() {
        if (ch_appno.isSelected()) {
            appNO = txt_appno.getText();
        } else {
            appNO = null;
        }

        if (ch_assesmant.isSelected()) {
            assessNo = txt_assesmant.getText();
        } else {
            assessNo = null;
        }

        if (ch_trade_type.isSelected()) {
            tradeType = com_trade_type.getSelectionModel().getSelectedItem();
            if (!tradeType.isEmpty()) {
                tt = new modle.TradeType().loadTreadType(tradeType).getIdTradeType();
            }
        } else {
            tradeType = null;
            tt = 0;
        }

        if (ch_trade_nature.isSelected()) {
            tradeNature = com_trade_nature.getSelectionModel().getSelectedItem();
        } else {
            tradeNature = null;
        }

        if (ch_ward.isSelected()) {
            ward = com_ward.getSelectionModel().getSelectedItem();
        } else {
            ward = null;
        }

        if (ch_street.isSelected()) {
            street = com_street.getSelectionModel().getSelectedItem();
        } else {
            street = null;
        }

        if (ch_Authoritist.isSelected()) {
            authoritist = com_authoritist.getSelectionModel().getSelectedItem();
            hassCat = true;
        } else {
            hassCat = false;
            authoritist = null;
        }

        if (ra_status_pending.isSelected()) {
            outhorityStatus = 0;
        } else if (ra_status_approve.isSelected()) {
            outhorityStatus = 1;
        } else if (ra_status_none.isSelected()) {
            outhorityStatus = 2;
        } else {
            outhorityStatus = 3;
        }

        if (ch_robill.isSelected()) {
            hasRO = true;
            if (ra_byroname.isSelected()) {
                roname = com_ronames.getSelectionModel().getSelectedItem();
            } else {
                com_ronames.setItems(null);
                roname = null;
            }
        } else {
            hasRO = false;
        }

        if (ch_pay.isSelected()) {
            //  ra_payNoneApprove.setSelected(true);
            hasPay = true;
            if (ra_payNoneApprove.isSelected()) {
                payStatus = 0;
            } else if (ra_payApprove.isSelected()) {
                payStatus = 1;
            } else if (ra_paid.isSelected()) {
                payStatus = 2;
            }

        } else {
            hasPay = false;
        }

        if (ch_date.isSelected()) {
            hasDate = true;
            LocalDate localDate = date_form.getValue();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                fromDate = Date.from(instant);
                //  System.out.println(localDate + "\n" + instant + "\n" + date);
                hasDate = true;
            } else {
                modle.Allert.notificationInfo("Cheack Date", "From");
                hasDate = false;
            }
            LocalDate localDateTo = date_to.getValue();
            if (localDateTo != null) {
                Instant instant = Instant.from(localDateTo.atStartOfDay(ZoneId.systemDefault()));
                toDate = Date.from(instant);
                //  System.out.println(localDateTo + "\n" + instant + "\n" + date);
                hasDate = true;
            } else {
                hasDate = false;
                modle.Allert.notificationInfo("Cheack Date", "To");
            }
        }

        if (loadAllAppList == null) {
            loadAllAppList = new modle.LoadAppList().loadAllAppList();
        }
        searchAppList();

    }
    //==========================================================

    @FXML
    private void search(ActionEvent event) {

        getData();

        // System.out.println(appNO);
        //   System.out.println(authoritist);
        //   System.out.println(outhorityStatus);
        //   System.out.println(hassCat);
    }

    //=================================================== Trade Type
    public void loadCombo() {
        List<pojo.TradeType> loadTreadType = new modle.TradeType().loadTreadType();
        ObservableList List = FXCollections.observableArrayList();
        for (pojo.TradeType tradeType1 : loadTreadType) {
            List.add(tradeType1.getTypeName());
        }

        com_trade_type.setItems(List);
    }

    @FXML
    private void loadTradType(ActionEvent event) {
        if (ch_trade_type.isSelected()) {
            loadCombo();
            //  loadTradeNature(event);
        } else {
            com_trade_type.setItems(null);
            tt = 0;
            com_trade_nature.setItems(null);
            ch_trade_nature.setSelected(false);

        }

    }

    int tt;

    @FXML
    private void getTradeType(ActionEvent event) {
        tradeType = com_trade_type.getSelectionModel().getSelectedItem();
        if (tradeType != null) {
            tt = new modle.TradeType().loadTreadType(tradeType).getIdTradeType();
            loadTradeNature(event);
        } else {
            tt = 0;
        }
    }

    //============================ Trade Nature
    @FXML
    private void loadTradeNature(ActionEvent event) {

        if (ch_trade_nature.isSelected()) {

            if (tt == 0) {
                modle.Allert.notificationInfo("Select Trade Type", "Please Select Trade Type First");
                ch_trade_nature.setSelected(false);
                com_trade_nature.setItems(null);
            } else {
                ObservableList natureList = FXCollections.observableArrayList();
                Nature tn = new modle.Nature();
                tn.setTradeType(new modle.TradeType().loadTreadType(tt));
                List<TradeNature> nlist = tn.getNaturesList();
                natureList.clear();
                for (pojo.TradeNature tnn : nlist) {
                    natureList.add(tnn.getNature());
                }
                com_trade_nature.setItems(natureList);

            }
        } else {
            com_trade_nature.setItems(null);
        }

    }

    //=============================== Warda
    @FXML
    private void loadWard(ActionEvent event) {

        if (ch_ward.isSelected()) {

            List<Ward> list = new modle.Ward().getList();
            ObservableList List = FXCollections.observableArrayList();
            for (Ward ward : list) {
                List.add(ward.getWardName());
            }
            com_ward.setItems(List);

        } else {
            com_ward.setItems(null);
        }
    }

    @FXML
    private void getWard(ActionEvent event) {
        loadStreet(event);
    }

    //==================================== Street
    @FXML
    private void loadStreet(ActionEvent event) {

        if (ch_street.isSelected()) {
            ward = com_ward.getSelectionModel().getSelectedItem();
            if (ward != null) {
                modle.Ward wardm = new modle.Ward();
                String selectedItem = com_ward.getSelectionModel().getSelectedItem();
                wardm.setWardname(selectedItem);
                ObservableList list = wardm.getStreetBySelectedWard();
                com_street.setItems(list);
            } else {
                modle.Allert.notificationInfo("Select Ward", "Please Select Ward First");
                ch_street.setSelected(false);
                com_street.setItems(null);
            }
        } else {
            com_street.setItems(null);
        }

    }

    ///=========================================== Authoritist
    public void loadOutherCombo() {
        List<pojo.Otheritiscat> list = new modle.Authority().getList();
        ObservableList aList = FXCollections.observableArrayList();
        try {
            list.forEach((dip) -> {
                aList.add(dip.getCatname());
            });
            com_authoritist.setItems(aList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void loadAuthoritst(ActionEvent event) {

        if (ch_Authoritist.isSelected()) {
            hassCat = true;
            ra_onebycat.setSelected(true);
            ra_status_pending.setSelected(true);
            loadOutherCombo();
        } else {
            authoritist = null;
            hassCat = false;

            ra_status_all.setSelected(false);
            ra_status_approve.setSelected(false);
            ra_status_none.setSelected(false);
            ra_status_pending.setSelected(false);
            ra_onebycat.setSelected(false);
            ra_status_pending.setSelected(false);
        }
    }

    @FXML
    private void loadCatagory(ActionEvent event) {
        if (ch_Authoritist.isSelected()) {
            loadOutherCombo();
            hassCat = true;
        } else {
            ra_onebycat.setSelected(false);
            hassCat = false;
        }

    }

    @FXML
    private void removeCat(ActionEvent event) {
        if (ch_Authoritist.isSelected()) {
            hassCat = true;
            com_authoritist.setItems(null);

            //com_authoritist.setItems("All");
        } else {
            ra_allcat.setSelected(false);
            hassCat = false;
        }
    }

    @FXML
    private void ra_all(ActionEvent event) {
        if (ch_Authoritist.isSelected()) {

        } else {
            ra_status_all.setSelected(false);
            ra_status_approve.setSelected(false);
            ra_status_none.setSelected(false);
            ra_status_pending.setSelected(false);
        }
    }

    @FXML
    private void ra_approve(ActionEvent event) {
        if (ch_Authoritist.isSelected()) {

        } else {
            ra_status_all.setSelected(false);
            ra_status_approve.setSelected(false);
            ra_status_none.setSelected(false);
            ra_status_pending.setSelected(false);
        }
    }

    @FXML
    private void ra_none(ActionEvent event) {
        if (ch_Authoritist.isSelected()) {

        } else {
            ra_status_all.setSelected(false);
            ra_status_approve.setSelected(false);
            ra_status_none.setSelected(false);
            ra_status_pending.setSelected(false);
        }
    }

    @FXML
    private void ra_pending(ActionEvent event) {
        if (ch_Authoritist.isSelected()) {

        } else {
            ra_status_all.setSelected(false);
            ra_status_approve.setSelected(false);
            ra_status_none.setSelected(false);
            ra_status_pending.setSelected(false);
        }
    }

    //============================================= RO RO RO
    public void loadROlist() {
        ObservableList aList = FXCollections.observableArrayList();
        List<String> roList = new modle.RO().getRoList();
        for (String string : roList) {
            aList.add(string);
        }
        com_ronames.setItems(aList);
    }

    @FXML
    private void loadRo(ActionEvent event) {
        if (ch_robill.isSelected()) {
            if (ra_byroname.isSelected()) {
                loadROlist();
            } else {
                com_ronames.setItems(null);
            }
        } else {
            ra_allRo.setSelected(false);
            ra_byroname.setSelected(false);
        }

    }

    @FXML
    private void robyname(ActionEvent event) {
        if (ch_robill.isSelected()) {
            if (ra_byroname.isSelected()) {
                loadROlist();
            } else {
                com_ronames.setItems(null);
            }
        } else {
            ra_allRo.setSelected(false);
            ra_byroname.setSelected(false);
        }
    }

    @FXML
    private void allRO(ActionEvent event) {
        if (ch_robill.isSelected()) {
            if (ra_byroname.isSelected()) {
                loadROlist();
            } else {
                com_ronames.setItems(null);
            }
        } else {
            ra_allRo.setSelected(false);
            ra_byroname.setSelected(false);
        }
    }
    //=============================================================== paymant

    @FXML
    private void loadPay(ActionEvent event) {
        if (ch_pay.isSelected()) {
            btn_paymant_report.setDisable(false);
            //  ra_payNoneApprove.setSelected(true);
            hasPay = true;
            if (ra_payNoneApprove.isSelected()) {
                payStatus = 0;
            } else if (ra_payApprove.isSelected()) {
                payStatus = 1;
            } else if (ra_paid.isSelected()) {
                payStatus = 2;
            }

        } else {
            ra_payApprove.setSelected(false);
            ra_paid.setSelected(false);
            ra_payNoneApprove.setSelected(false);
            btn_paymant_report.setDisable(true);
        }

    }

    //Date ===============================================
    @FXML
    private void loadDate(ActionEvent event) {
        if (ch_date.isSelected()) {
            LocalDate localDate = date_form.getValue();
            if (localDate != null) {
                Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
                Date date = Date.from(instant);
                //   System.out.println(localDate + "\n" + instant + "\n" + date);
            }
            LocalDate localDateTo = date_to.getValue();
            if (localDateTo != null) {
                Instant instant = Instant.from(localDateTo.atStartOfDay(ZoneId.systemDefault()));
                Date date = Date.from(instant);
                //   System.out.println(localDateTo + "\n" + instant + "\n" + date);
            }
            btn_paymant_report.setDisable(false);
        } else {
            btn_paymant_report.setDisable(true);
        }

    }

    ObservableList appList = FXCollections.observableArrayList();

    public void loadTable() {

        c_idApp.setCellValueFactory(new PropertyValueFactory<>("appno"));
        c_idApp.setCellValueFactory(new PropertyValueFactory<>("appNOString"));
        c_type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        c_nature.setCellValueFactory(new PropertyValueFactory<>("nature"));
        c_alocation.setCellValueFactory(new PropertyValueFactory<>("alocation"));
        c_tax.setCellValueFactory(new PropertyValueFactory<>("txt"));
        c_approve.setCellValueFactory(new PropertyValueFactory<>("payapp"));
        c_tname.setCellValueFactory(new PropertyValueFactory<>("Tname"));

        appList.clear();

        if (filter != null) {
            filter.forEach((a) -> {
                appList.add(new AppTbl(a.getIdApplication(), a.getApplicationNo(), a.getTradeTypeName(), a.getNature(), a.getAllocation(), a.getTaxAmount(), a.getApproveToPaymant(), a.getTradeName()));
            });
        }
        tbl_applicaion.setItems(appList);

    }

    @FXML
    private void more(ActionEvent event) {

        try {
            modle.StaticBadu.setApp(app);
            AnchorPane paymant = javafx.fxml.FXMLLoader.load(getClass().getResource("/view/Ditails.fxml"));
            btn_All.getParent().getScene();
            Scene scene = new Scene(paymant);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
            Logger
                    .getLogger(PayController.class
                            .getName()).log(Level.SEVERE, null, ex);
        }

    }

    pojo.Application app;

    @FXML
    private void selectApp(MouseEvent event) {
        int appno = tbl_applicaion.getSelectionModel().getSelectedItem().getAppno();
        Session session = conn.NewHibernateUtil.getSessionFactory().openSession();

        try {
            app = (pojo.Application) session.createCriteria(pojo.Application.class
            ).add(Restrictions.eq("idApplication", appno)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @FXML
    private void export(ActionEvent event) {
        Export_TO_Excel export_TO_Excel = new modle.Export_TO_Excel();
        export_TO_Excel.setSet(filter);
    }

}

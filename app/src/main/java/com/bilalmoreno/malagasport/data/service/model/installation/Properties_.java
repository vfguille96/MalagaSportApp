
package com.bilalmoreno.malagasport.data.service.model.installation;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Properties_ {

    @SerializedName("TITULARIDAD")
    @Expose
    private String tITULARIDAD;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("PRECIOS")
    @Expose
    private Object pRECIOS;
    @SerializedName("DIRECCION")
    @Expose
    private String dIRECCION;
    @SerializedName("ACCESOPMR")
    @Expose
    private String aCCESOPMR;
    @SerializedName("INFOESP")
    @Expose
    private List<INFOESP> iNFOESP = null;
    @SerializedName("TARJETAJOVEN")
    @Expose
    private String tARJETAJOVEN;
    @SerializedName("NOMBRE")
    @Expose
    private String nOMBRE;
    @SerializedName("DESCRIPCION")
    @Expose
    private Object dESCRIPCION;
    @SerializedName("HORARIOS")
    @Expose
    private Object hORARIOS;
    @SerializedName("CONTACTO")
    @Expose
    private Object cONTACTO;
    @SerializedName("EMAIL")
    @Expose
    private String eMAIL;
    @SerializedName("ID")
    @Expose
    private Integer iD;

    public String getTITULARIDAD() {
        return tITULARIDAD;
    }

    public void setTITULARIDAD(String tITULARIDAD) {
        this.tITULARIDAD = tITULARIDAD;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public Object getPRECIOS() {
        return pRECIOS;
    }

    public void setPRECIOS(Object pRECIOS) {
        this.pRECIOS = pRECIOS;
    }

    public String getDIRECCION() {
        return dIRECCION;
    }

    public void setDIRECCION(String dIRECCION) {
        this.dIRECCION = dIRECCION;
    }

    public String getACCESOPMR() {
        return aCCESOPMR;
    }

    public void setACCESOPMR(String aCCESOPMR) {
        this.aCCESOPMR = aCCESOPMR;
    }

    public List<INFOESP> getINFOESP() {
        return iNFOESP;
    }

    public void setINFOESP(List<INFOESP> iNFOESP) {
        this.iNFOESP = iNFOESP;
    }

    public String getTARJETAJOVEN() {
        return tARJETAJOVEN;
    }

    public void setTARJETAJOVEN(String tARJETAJOVEN) {
        this.tARJETAJOVEN = tARJETAJOVEN;
    }

    public String getNOMBRE() {
        return nOMBRE;
    }

    public void setNOMBRE(String nOMBRE) {
        this.nOMBRE = nOMBRE;
    }

    public Object getDESCRIPCION() {
        return dESCRIPCION;
    }

    public void setDESCRIPCION(Object dESCRIPCION) {
        this.dESCRIPCION = dESCRIPCION;
    }

    public Object getHORARIOS() {
        return hORARIOS;
    }

    public void setHORARIOS(Object hORARIOS) {
        this.hORARIOS = hORARIOS;
    }

    public Object getCONTACTO() {
        return cONTACTO;
    }

    public void setCONTACTO(Object cONTACTO) {
        this.cONTACTO = cONTACTO;
    }

    public String getEMAIL() {
        return eMAIL;
    }

    public void setEMAIL(String eMAIL) {
        this.eMAIL = eMAIL;
    }

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

}

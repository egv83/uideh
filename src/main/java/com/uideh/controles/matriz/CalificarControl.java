package com.uideh.controles.matriz;

import com.uideh.dao.AgentesDAO;
import com.uideh.dao.variables.ActividadesProductivasDAO;
import com.uideh.dao.variables.AutorizacionProyectoDAO;
import com.uideh.dao.variables.DesarrollOperativoDAO;
import com.uideh.dao.variables.DesarrollOperativoInvestigacionDAO;
import com.uideh.dao.variables.DesarrolloInvestigacionDAO;
import com.uideh.dao.variables.InicioInvestigacionDAO;
import com.uideh.dao.variables.PresentacionProyectoDAO;
import com.uideh.dao.variables.ResultadosDAO;
import com.uideh.dao.variables.VerificarInformacionDAO;
import com.uideh.model.Agente;
import com.uideh.model.Matriz;
import com.uideh.util.Utilidades;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Esteban Vallejo
 */
@ManagedBean
@SessionScoped
public class CalificarControl extends Utilidades implements Serializable {

    @EJB
    AgentesDAO agentesDAO;
    @EJB
    VerificarInformacionDAO verificarInformacionDAO;
    @EJB
    DesarrollOperativoDAO desarrollOperativoDAO;
    @EJB
    ResultadosDAO resultadosDAO;
    @EJB
    PresentacionProyectoDAO presentacionProyectoDAO;
    @EJB
    AutorizacionProyectoDAO autorizacionProyectoDAO;
    @EJB
    InicioInvestigacionDAO inicioInvestigacionDAO;
    @EJB
    DesarrolloInvestigacionDAO desarrolloInvestigacionDAO;
    @EJB
    DesarrollOperativoInvestigacionDAO desarrollOperativoInvestigacionDAO;
    @EJB
    ActividadesProductivasDAO actividadesProductivasDAO;

    private Matriz matriz;
    private Agente agente;
    private Agente selectedAgente;
    private Boolean flagDos;
    private Long[] selectedVerifInf;
    private Long[] selectedDesOper;
    private Long[] selectedResult;
    private Long[] selectedPresProyect;
    private Long[] selectedAutProyect;
    private Long[] selectedInicioInvest;
    private Long[] selectedDesaInvestig;
    private Long[] selectedDesaOperativ;
    private Long[] selectedResultInv;
    private Long[] selectedActProduct;

    private Long sumaVerInf;
    private Long sumaDesOper;
    private Long sumaResult;
    private Long sumaPresProy;
    private Long sumaAutProyect;
    private Long sumaInicioInvest;
    private Long sumaDesaInvestig;
    private Long sumaDesaOperativ;
    private Long sumaResultInv;
    private Long sumaActProduct;

    private Long tmpResultFlag;
    private Long tmpResultInvest;
    private Long tmpActivProductivas;
    private Long investMayor;
    private Long sumatoriaFlagrancia;
    private Long sumatoriaProyectoInvestig;

    private Long totalFlag;
    private Long totalInvest;
    private Long totalActivProduct;
    private Long total;

    public CalificarControl() {
    }

    @PostConstruct
    public void inicio() {
        this.matriz = new Matriz();
        this.matriz.setFecha(getFechaActual());

        /**
         * *
         * VARIABLES DE FLAGRANCIA
         */
        this.sumaVerInf = new Long(0);
        this.sumaDesOper = new Long(0);
        this.tmpResultFlag = new Long(0);

        /**
         * VARIABLES DE INVESTIGACIÓN
         */
        this.sumaAutProyect = new Long(0);
        this.sumaInicioInvest = new Long(0);
        this.sumaDesaInvestig = new Long(0);
        this.sumaDesaOperativ = new Long(0);
        this.tmpResultInvest = new Long(0);

        /**
         * VARIABLES ACTIVIDADES PRODUCTIVAS
         */
        this.tmpActivProductivas = new Long(0);
        this.investMayor = new Long(0);

        /**
         * VARIABLES PARA LA SUMATORIAS DE FLAGRANCIA, INVESTIGACIÓN y
         * ACTIVIDADES PRODUCTIVAS
         */
        this.sumatoriaFlagrancia = new Long(0);
        this.sumatoriaProyectoInvestig = new Long(0);
        this.totalFlag = new Long(0);
        this.totalInvest = new Long(0);
        this.totalActivProduct = new Long(0);
        this.total = new Long(0);
    }

    public String getAgenteNombre() {
        System.out.println("ENTRO EN GET AGENTE COMBRE");
        System.out.println("VALOR DE AGENTE: " + this.getMatriz().getAgente());
        if (this.getSelectedAgente() != null) {
            return this.getSelectedAgente().getNombres() + " " + this.getSelectedAgente().getApellidos();
        }
        return "";
    }

    public void grabar() {
        System.out.println("ENTRO EN GUARDAR");
        for (Long verInf : this.getSelectedVerifInf()) {
            addSuccessMessage(verInf.toString());
        }
        for (Long desOper : this.getSelectedDesOper()) {
            addSuccessMessage(desOper.toString());
        }
        for (Long result : this.getSelectedResult()) {
            addSuccessMessage(result.toString());
        }
    }

    public void calcularVerInf() {
        System.out.println("ENTRO EN CALCULAR");
        this.sumaVerInf = new Long(0);

        for (Long verInf : this.getSelectedVerifInf()) {
            System.out.println("ENTRO EN PRIMER FOR");
            sumaVerInf = sumaVerInf + verificarInformacionDAO.verifInformacion(verInf).getPonderacion();
        }
        this.calculoSumatoriaFlagracia();
    }

    public void calcularDesOper() {
        System.out.println("ENTRO EN CALCULO DESOPER");
        this.sumaDesOper = new Long(0);
        for (Long desOper : this.getSelectedDesOper()) {
            System.out.println("ID: " + desOper);
            this.sumaDesOper = sumaDesOper + desarrollOperativoDAO.desOperativoId(desOper).getPonderacion();
        }
        this.calculoSumatoriaFlagracia();
    }

    public void calcularResult() {
        this.sumaResult = new Long(0);
        for (Long result : this.getSelectedResult()) {
            this.sumaResult = sumaResult + resultadosDAO.resultadosId(result).getPonderacion();
        }
        if (this.sumaResult > 15) {
            this.tmpResultFlag = new Long(60);
        } else if (this.sumaResult >= 10 && this.sumaResult <= 15) {
            this.tmpResultFlag = new Long(50);
        } else if (this.sumaResult >= 5 && this.sumaResult <= 10) {
            this.tmpResultFlag = new Long(40);
        } else {
            this.tmpResultFlag = new Long(0);
        }
        this.calculoSumatoriaFlagracia();
    }

    public void calculoPresProyect() {
        this.sumaPresProy = new Long(0);
        for (Long presProy : this.getSelectedPresProyect()) {
            this.sumaPresProy = sumaPresProy + presentacionProyectoDAO.presProyectId(presProy).getPonderacion();
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoAutoProyect() {
        this.sumaAutProyect = new Long(0);
        for (Long autProyect : this.getSelectedAutProyect()) {
            this.sumaAutProyect = sumaAutProyect + autorizacionProyectoDAO.autoProyectId(autProyect).getPonderacion();
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoInicioInvest() {
        this.sumaInicioInvest = new Long(0);
        for (Long inicInvest : this.getSelectedInicioInvest()) {
            this.sumaInicioInvest = sumaInicioInvest + inicioInvestigacionDAO.inicInvetigacionId(inicInvest).getPonderacion();
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoDesaInvestig() {
        this.sumaDesaInvestig = new Long(0);
        for (Long desaInvestig : this.getSelectedDesaInvestig()) {
            this.sumaDesaInvestig = this.sumaDesaInvestig + desarrolloInvestigacionDAO.desaInvestigacionId(desaInvestig).getPonderacion();
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoDesaOperativ() {
        this.sumaDesaOperativ = new Long(0);
        for (Long desaOperativ : this.getSelectedDesaOperativ()) {
            this.sumaDesaOperativ = sumaDesaOperativ + desarrollOperativoInvestigacionDAO.desaoperativoId(desaOperativ).getPonderacion();
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoResultInvest() {
        this.sumaResultInv = new Long(0);
        for (Long resultInv : this.getSelectedResultInv()) {
            this.sumaResultInv = sumaResultInv + resultadosDAO.resultadosId(resultInv).getPonderacion();
        }

        if (this.sumaResultInv > 20) {
            this.tmpResultInvest = new Long(25);
        } else if (this.sumaResultInv >= 10 && this.sumaResultInv <= 20) {
            this.tmpResultInvest = new Long(20);
        } else if (this.sumaResultInv >= 1 && this.sumaResultInv <= 10) {
            this.tmpResultInvest = new Long(15);
        } else {
            this.tmpResultInvest = new Long(0);
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoActProduct() {
        this.sumaActProduct = new Long(0);
        this.totalActivProduct = new Long(0);
        if (this.getSelectedActProduct() != null && this.getSelectedActProduct().length != 0) {
            for (Long actProduct : this.getSelectedActProduct()) {
                this.sumaActProduct = sumaActProduct + actividadesProductivasDAO.actProductivasId(actProduct).getPonderacion();
            }
        }

        if (this.sumaActProduct >= 10 && this.sumaActProduct <= 18) {
            this.tmpActivProductivas = new Long(10);
        } else if (this.sumaActProduct >= 1 && this.sumaActProduct <= 9) {
            this.tmpActivProductivas = new Long(5);
        } else {
            this.tmpActivProductivas = new Long(0);
        }

        System.out.println("ANTES DE IF MATRIZ");
        if (this.matriz != null && this.matriz.getIdmatriz() != null) {
            System.out.println("INSTANCIA MATRIZ: " + this.matriz);
            if (this.matriz.getAgente() != null && this.matriz.getAgente().getJefe()) {
                System.out.println("INSTANCIA MATRIZ AGENTE: " + this.matriz.getAgente());
                this.totalActivProduct = (this.sumatoriaActProduct() * 50) / 60;
                this.total = this.totalFlag + this.totalInvest + this.totalActivProduct;
            }
        } else {
            this.totalActivProduct = this.sumatoriaActProduct();
        }
        this.total = this.totalFlag + this.totalInvest + this.totalActivProduct;
    }

    private Long sumatoriaActProduct() {
        Long sumActivProduct = new Long(0);
        sumActivProduct = this.tmpActivProductivas + this.investMayor;
        if (this.flagDos != null && this.flagDos) {
            sumActivProduct = sumActivProduct + 40;
        }
        return sumActivProduct;
    }

    private void calculoSumatoriaFlagracia() {
        this.sumatoriaFlagrancia = new Long(0);
        //if(this.sumaVerInf != null  && this.sumaDesOper != null 
        //        && this.tmpResultFlag != null ){
        this.sumatoriaFlagrancia = this.sumaVerInf + this.sumaDesOper + this.tmpResultFlag;
        //}

        System.out.println("ANTES DE IF SELECTED AGENTE SUMATORIA FLAG");
        if (this.getSelectedAgente() != null) {
            System.out.println("INSTANCIA MATRIZ: " + this.getSelectedAgente());
            if (this.getSelectedAgente().getJefe()) {
                System.out.println("INSTANCIA MATRIZ AGENTE: " + this.getSelectedAgente().getJefe());
                this.totalFlag = (this.sumatoriaFlagrancia * 10) / 100;
            }
        } else {
            this.totalFlag = (this.sumatoriaFlagrancia * 20) / 100;
        }

        this.total = this.totalFlag + this.totalInvest + this.totalActivProduct;
        System.out.println("RESULTADO SUMATORIA FLAG: " + this.sumatoriaFlagrancia);
    }

    private void calculoSumatoriaProyectoInvestigacion() {
        this.sumatoriaProyectoInvestig = new Long(0);
        //if(this.sumaPresProy != null && this.sumaPresProy !=0 && this.sumaAutProyect != null && this.sumaAutProyect != 0
        //        && this.sumaInicioInvest != null && this.sumaInicioInvest != 0 && this.sumaDesaInvestig != null && this.sumaDesaInvestig != 0
        //        && this.sumaDesaOperativ != null && this.sumaDesaOperativ != 0 && this.tmpResultInvest != null && this.tmpResultInvest !=0){
        this.sumatoriaProyectoInvestig = this.sumaPresProy + this.sumaAutProyect + this.sumaInicioInvest
                + this.sumaDesaInvestig + this.sumaDesaOperativ + this.tmpResultInvest;
        //}
        if (this.sumatoriaProyectoInvestig > 20) {
            this.investMayor = new Long(10);
        } else {
            this.investMayor = new Long(0);
        }

        this.totalInvest = (this.sumatoriaProyectoInvestig * 20) / 100;
        this.total = this.totalFlag + this.totalInvest + this.totalActivProduct;
        System.out.println("RESULTADO SUMATORIA INVEST: " + this.sumatoriaProyectoInvestig);
    }

    public void jefeAgente() {
        addSuccessMessage(this.getSelectedAgente().getJefe().toString());
    }

    public Matriz getMatriz() {
        return matriz;
    }

    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
    }

    public Date getFecha() {
        return getFechaActual();
    }

    /*public void setFecha(Date fecha) {
        this.fecha = fecha;
    }*/
    public Long[] getSelectedVerifInf() {
        return selectedVerifInf;
    }

    public void setSelectedVerifInf(Long[] selectedVerifInf) {
        this.selectedVerifInf = selectedVerifInf;
    }

    public Long[] getSelectedDesOper() {
        return selectedDesOper;
    }

    public void setSelectedDesOper(Long[] selectedDesOper) {
        this.selectedDesOper = selectedDesOper;
    }

    public Long[] getSelectedResult() {
        return selectedResult;
    }

    public void setSelectedResult(Long[] selectedResult) {
        this.selectedResult = selectedResult;
    }

    public Long[] getSelectedPresProyect() {
        return selectedPresProyect;
    }

    public void setSelectedPresProyect(Long[] selectedPresProyect) {
        this.selectedPresProyect = selectedPresProyect;
    }

    public Long[] getSelectedAutProyect() {
        return selectedAutProyect;
    }

    public void setSelectedAutProyect(Long[] selectedAutProyect) {
        this.selectedAutProyect = selectedAutProyect;
    }

    public Long[] getSelectedInicioInvest() {
        return selectedInicioInvest;
    }

    public void setSelectedInicioInvest(Long[] selectedInicioInvest) {
        this.selectedInicioInvest = selectedInicioInvest;
    }

    public Long[] getSelectedDesaInvestig() {
        return selectedDesaInvestig;
    }

    public void setSelectedDesaInvestig(Long[] selectedDesaInvestig) {
        this.selectedDesaInvestig = selectedDesaInvestig;
    }

    public Long[] getSelectedDesaOperativ() {
        return selectedDesaOperativ;
    }

    public void setSelectedDesaOperativ(Long[] selectedDesaOperativ) {
        this.selectedDesaOperativ = selectedDesaOperativ;
    }

    public Long[] getSelectedResultInv() {
        return selectedResultInv;
    }

    public void setSelectedResultInv(Long[] selectedResultInv) {
        this.selectedResultInv = selectedResultInv;
    }

    public Long getSumaVerInf() {
        return sumaVerInf;
    }

    public void setSumaVerInf(Long sumaVerInf) {
        this.sumaVerInf = sumaVerInf;
    }

    public Long getSumaDesOper() {
        return sumaDesOper;
    }

    public void setSumaDesOper(Long sumaDesOper) {
        this.sumaDesOper = sumaDesOper;
    }

    public Long getSumaResult() {
        return sumaResult;
    }

    public void setSumaResult(Long sumaResult) {
        this.sumaResult = sumaResult;
    }

    public Long getSumaPresProy() {
        return sumaPresProy;
    }

    public void setSumaPresProy(Long sumaPresProy) {
        this.sumaPresProy = sumaPresProy;
    }

    public Long getSumaAutProyect() {
        return sumaAutProyect;
    }

    public void setSumaAutProyect(Long sumaAutProyect) {
        this.sumaAutProyect = sumaAutProyect;
    }

    public Long[] getSelectedActProduct() {
        return selectedActProduct;
    }

    public void setSelectedActProduct(Long[] selectedActProduct) {
        this.selectedActProduct = selectedActProduct;
    }

    public Long getSumaInicioInvest() {
        return sumaInicioInvest;
    }

    public void setSumaInicioInvest(Long sumaInicioInvest) {
        this.sumaInicioInvest = sumaInicioInvest;
    }

    public Long getSumaDesaInvestig() {
        return sumaDesaInvestig;
    }

    public void setSumaDesaInvestig(Long sumaDesaInvestig) {
        this.sumaDesaInvestig = sumaDesaInvestig;
    }

    public Long getSumaDesaOperativ() {
        return sumaDesaOperativ;
    }

    public void setSumaDesaOperativ(Long sumaDesaOperativ) {
        this.sumaDesaOperativ = sumaDesaOperativ;
    }

    public Long getSumaResultInv() {
        return sumaResultInv;
    }

    public void setSumaResultInv(Long sumaResultInv) {
        this.sumaResultInv = sumaResultInv;
    }

    public Long getSumaActProduct() {
        return sumaActProduct;
    }

    public void setSumaActProduct(Long sumaActProduct) {
        this.sumaActProduct = sumaActProduct;
    }

    public Long getTmpResultFlag() {
        return tmpResultFlag;
    }

    public void setTmpResultFlag(Long tmpResultFlag) {
        this.tmpResultFlag = tmpResultFlag;
    }

    public Long getTmpResultInvest() {
        return tmpResultInvest;
    }

    public void setTmpResultInvest(Long tmpResultInvest) {
        this.tmpResultInvest = tmpResultInvest;
    }

    public Long getSumatoriaFlagrancia() {
        return sumatoriaFlagrancia;
    }

    public void setSumatoriaFlagrancia(Long sumatoriaFlagrancia) {
        this.sumatoriaFlagrancia = sumatoriaFlagrancia;
    }

    public Long getSumatoriaProyectoInvestig() {
        return sumatoriaProyectoInvestig;
    }

    public void setSumatoriaProyectoInvestig(Long sumatoriaProyectoInvestig) {
        this.sumatoriaProyectoInvestig = sumatoriaProyectoInvestig;
    }

    public Long getTotalFlag() {
        return totalFlag;
    }

    public void setTotalFlag(Long totalFlag) {
        this.totalFlag = totalFlag;
    }

    public Long getTotalInvest() {
        return totalInvest;
    }

    public void setTotalInvest(Long totalInvest) {
        this.totalInvest = totalInvest;
    }

    public Long getTmpActivProductivas() {
        return tmpActivProductivas;
    }

    public void setTmpActivProductivas(Long tmpActivProductivas) {
        this.tmpActivProductivas = tmpActivProductivas;
    }

    public Long getTotalActivProduct() {
        return totalActivProduct;
    }

    public void setTotalActivProduct(Long totalActivProduct) {
        this.totalActivProduct = totalActivProduct;
    }

    public Long getInvestMayor() {
        return investMayor;
    }

    public void setInvestMayor(Long investMayor) {
        this.investMayor = investMayor;
    }

    public Boolean getFlagDos() {
        return flagDos;
    }

    public void setFlagDos(Boolean flagDos) {
        this.flagDos = flagDos;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Agente getSelectedAgente() {
        return selectedAgente;
    }

    public void setSelectedAgente(Agente selectedAgente) {
        this.selectedAgente = selectedAgente;
    }

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

}

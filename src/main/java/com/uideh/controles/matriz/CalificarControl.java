package com.uideh.controles.matriz;

import com.uideh.dao.AgenciaDAO;
import com.uideh.dao.AgentesDAO;
import com.uideh.dao.matriz.MatrizDAO;
import com.uideh.dao.variables.ActividadesProductivasDAO;
import com.uideh.dao.variables.AutorizacionProyectoDAO;
import com.uideh.dao.variables.DesarrollOperativoDAO;
import com.uideh.dao.variables.DesarrollOperativoInvestigacionDAO;
import com.uideh.dao.variables.DesarrolloInvestigacionDAO;
import com.uideh.dao.variables.InicioInvestigacionDAO;
import com.uideh.dao.variables.PresentacionProyectoDAO;
import com.uideh.dao.variables.ResultadosDAO;
import com.uideh.dao.variables.VerificarInformacionDAO;
import com.uideh.model.Agencia;
import com.uideh.model.Agente;
import com.uideh.model.Matriz;
import com.uideh.util.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    MatrizDAO matrizDAO;
    @EJB
    AgenciaDAO agenciaDAO;
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
    private List<Long> selectedVerifInf;
    private List<Long> selectedDesOper;
    private List<Long> selectedResult;
    private List<Long> selectedPresProyect;
    private List<Long> selectedAutProyect;
    private List<Long> selectedInicioInvest;
    private List<Long> selectedDesaInvestig;
    private List<Long> selectedDesaOperativ;
    private List<Long> selectedResultInv;
    private List<Long> selectedActProduct;

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
        //this.selectedAgente

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
        if (this.getSelectedAgente() != null) {
            return this.getSelectedAgente().getNombres() + " " + this.getSelectedAgente().getApellidos();
        }
        return "";
    }

    public void grabar() {
        try {
            System.out.println("EN GREBAR");
            System.out.println("TOTAL FLAG: " + this.totalFlag);
            System.out.println("TOTAL INVEST: " + this.totalInvest);
            System.out.println("TOTAL ActivProduct: " + this.totalActivProduct);
            if (this.getSelectedAgente()!=null&&this.totalFlag != 0 && this.totalInvest != 0 && this.totalActivProduct != 0) {
                this.getMatriz().setIdAgente(this.getSelectedAgente());
                this.getMatriz().setActivProductivas(this.getTotalActivProduct());
                this.getMatriz().setInvestigacion(this.getTotalInvest());
                this.getMatriz().setFlagrancia(this.getTotalFlag());
                this.getMatriz().setTotal(this.getTotal());
                matrizDAO.crear(this.getMatriz());
                this.limpiar();
                addSuccessMessage("Se guardo con exito la calificación");
            } else {
                addWarningMessage("Tiene que seleccionar un agente y hacer la selección de una de las 3 secciones de variables");
            }
        } catch (Exception e) {
            addErrorMessage(e.getMessage());
        } finally {

        }

    }

    public void limpiar() {
        this.matriz = new Matriz();
        this.matriz.setFecha(getFechaActual());
        this.selectedAgente = null;

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

        /**
         * LISTAS DE VARIABLES
         */
        if (this.selectedActProduct != null && !this.selectedActProduct.isEmpty()) {
            this.selectedActProduct.clear();
        }
        if (this.selectedAutProyect != null && !this.selectedAutProyect.isEmpty()) {
            this.selectedAutProyect.clear();
        }
        if (this.selectedDesOper != null && !this.selectedDesOper.isEmpty()) {
            this.selectedDesOper.clear();
        }
        if (this.selectedDesaInvestig != null && !this.selectedDesaInvestig.isEmpty()) {
            this.selectedDesaInvestig.clear();
        }
        if (this.selectedDesaOperativ != null && !this.selectedDesaOperativ.isEmpty()) {
            this.selectedDesaOperativ.clear();
        }
        if (this.selectedInicioInvest != null && !this.selectedInicioInvest.isEmpty()) {
            this.selectedInicioInvest.clear();
        }
        if (this.selectedPresProyect != null && !this.selectedPresProyect.isEmpty()) {
            this.selectedPresProyect.clear();
        }
        if (this.selectedResult != null && !this.selectedResult.isEmpty()) {
            this.selectedResult.clear();
        }
        if (this.selectedResultInv != null && !this.selectedResultInv.isEmpty()) {
            this.selectedResultInv.clear();
        }
        if (this.selectedVerifInf != null && !this.selectedVerifInf.isEmpty()) {
            this.selectedVerifInf.clear();
        }

    }

    public void calcularVerInf() {
        this.sumaVerInf = new Long(0);
        for (Object verInf : this.getSelectedVerifInf().toArray()) {
            sumaVerInf = sumaVerInf + verificarInformacionDAO.verifInformacion(new Long(verInf.toString())).getPonderacion();
        }
        /*for (Long verInf : this.getSelectedVerifInf()) {
            sumaVerInf = sumaVerInf + verificarInformacionDAO.verifInformacion(verInf).getPonderacion();
        }*/
        this.calculoSumatoriaFlagracia();
    }

    public void calcularDesOper() {
        this.sumaDesOper = new Long(0);
        for (Object desOper : this.getSelectedDesOper().toArray()) {
            this.sumaDesOper = sumaDesOper + desarrollOperativoDAO.desOperativoId(new Long(desOper.toString())).getPonderacion();
        }
        this.calculoSumatoriaFlagracia();
    }

    public void calcularResult() {
        this.sumaResult = new Long(0);
        for (Object result : this.getSelectedResult().toArray()) {
            this.sumaResult = sumaResult + resultadosDAO.resultadosId(new Long(result.toString())).getPonderacion();
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
        for (Object presProy : this.getSelectedPresProyect().toArray()) {
            this.sumaPresProy = sumaPresProy + presentacionProyectoDAO.presProyectId(new Long(presProy.toString())).getPonderacion();
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoAutoProyect() {
        this.sumaAutProyect = new Long(0);
        for (Object autProyect : this.getSelectedAutProyect().toArray()) {
            this.sumaAutProyect = sumaAutProyect + autorizacionProyectoDAO.autoProyectId(new Long(autProyect.toString())).getPonderacion();
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoInicioInvest() {
        this.sumaInicioInvest = new Long(0);
        for (Object inicInvest : this.getSelectedInicioInvest().toArray()) {
            this.sumaInicioInvest = sumaInicioInvest + inicioInvestigacionDAO.inicInvetigacionId(new Long(inicInvest.toString())).getPonderacion();
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoDesaInvestig() {
        this.sumaDesaInvestig = new Long(0);
        for (Object desaInvestig : this.getSelectedDesaInvestig().toArray()) {
            this.sumaDesaInvestig = this.sumaDesaInvestig + desarrolloInvestigacionDAO.desaInvestigacionId(new Long(desaInvestig.toString())).getPonderacion();
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoDesaOperativ() {
        this.sumaDesaOperativ = new Long(0);
        for (Object desaOperativ : this.getSelectedDesaOperativ().toArray()) {
            this.sumaDesaOperativ = sumaDesaOperativ + desarrollOperativoInvestigacionDAO.desaoperativoId(new Long(desaOperativ.toString())).getPonderacion();
        }
        this.calculoSumatoriaProyectoInvestigacion();
    }

    public void calculoResultInvest() {
        this.sumaResultInv = new Long(0);
        for (Object resultInv : this.getSelectedResultInv().toArray()) {
            this.sumaResultInv = sumaResultInv + resultadosDAO.resultadosId(new Long(resultInv.toString())).getPonderacion();
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
        if (this.getSelectedActProduct() != null) {
            for (Object actProduct : this.getSelectedActProduct().toArray()) {
                this.sumaActProduct = sumaActProduct + actividadesProductivasDAO.actProductivasId(new Long(actProduct.toString())).getPonderacion();
            }
        }

        if (this.sumaActProduct >= 10 && this.sumaActProduct <= 18) {
            this.tmpActivProductivas = new Long(10);
        } else if (this.sumaActProduct >= 1 && this.sumaActProduct <= 9) {
            this.tmpActivProductivas = new Long(5);
        } else {
            this.tmpActivProductivas = new Long(0);
        }

        if (this.getSelectedAgente() != null && this.getSelectedAgente().getJefe()) {
                this.totalActivProduct = (this.sumatoriaActProduct() * 50) / 60;
                //this.total = this.totalFlag + this.totalInvest + this.totalActivProduct;       
        } else {
            this.totalActivProduct = this.sumatoriaActProduct();
        }
        this.getMatriz().setTotal(this.totalFlag + this.totalInvest + this.totalActivProduct);
    }

    private Long sumatoriaActProduct() {
        Long sumActivProduct = new Long(0);
        System.out.println("Temp Activ Product: "+this.tmpActivProductivas);
        System.out.println("Investiga Mayor 20: "+this.investMayor);
        sumActivProduct = this.tmpActivProductivas + this.investMayor;
        if (this.flagDos != null && this.flagDos) {
            sumActivProduct = sumActivProduct + 40;
        }
        return sumActivProduct;
    }

    private void calculoSumatoriaFlagracia() {
        this.sumatoriaFlagrancia = new Long(0);
        this.sumatoriaFlagrancia = this.sumaVerInf + this.sumaDesOper + this.tmpResultFlag;
        System.out.println("CALCULO FLAGRANCIA");
        System.out.println("SELECTED AGENTE: " + this.getSelectedAgente());
        if (this.getSelectedAgente() != null && this.getSelectedAgente().getJefe()) {
            System.out.println("ES JEFE: " + this.getSelectedAgente().getJefe());
            this.totalFlag = (this.sumatoriaFlagrancia * 10) / 100;
        } else {
            System.out.println("ENTRO SI NO ES JEFE EN CALCULO SUMATORIA FLAG");
            this.totalFlag = (this.sumatoriaFlagrancia * 20) / 100;
        }
        this.getMatriz().setTotal(this.totalFlag + this.totalInvest + this.totalActivProduct);
    }

    private void calculoSumatoriaProyectoInvestigacion() {
        this.sumatoriaProyectoInvestig = new Long(0);
        this.sumatoriaProyectoInvestig = this.sumaPresProy + this.sumaAutProyect + this.sumaInicioInvest
                + this.sumaDesaInvestig + this.sumaDesaOperativ + this.tmpResultInvest;
        if (this.sumatoriaProyectoInvestig > 20) {
            this.investMayor = new Long(10);
        } else {
            this.investMayor = new Long(0);
        }
        this.totalInvest = (this.sumatoriaProyectoInvestig * 20) / 100;
        this.getMatriz().setTotal(this.totalFlag + this.totalInvest + this.totalActivProduct);
    }

    public List<String> getAgencias() {
        List<String> lista = new ArrayList<String>();
        for (Agencia agencia : agenciaDAO.allAgencias()) {
            lista.add(agencia.getNombre());
        }
        return lista;
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

    public List<Long> getSelectedVerifInf() {
        return selectedVerifInf;
    }

    public void setSelectedVerifInf(List<Long> selectedVerifInf) {
        this.selectedVerifInf = selectedVerifInf;
    }

    public List<Long> getSelectedDesOper() {
        return selectedDesOper;
    }

    public void setSelectedDesOper(List<Long> selectedDesOper) {
        this.selectedDesOper = selectedDesOper;
    }

    public List<Long> getSelectedResult() {
        return selectedResult;
    }

    public void setSelectedResult(List<Long> selectedResult) {
        this.selectedResult = selectedResult;
    }

    public List<Long> getSelectedPresProyect() {
        return selectedPresProyect;
    }

    public void setSelectedPresProyect(List<Long> selectedPresProyect) {
        this.selectedPresProyect = selectedPresProyect;
    }

    public List<Long> getSelectedAutProyect() {
        return selectedAutProyect;
    }

    public void setSelectedAutProyect(List<Long> selectedAutProyect) {
        this.selectedAutProyect = selectedAutProyect;
    }

    public List<Long> getSelectedInicioInvest() {
        return selectedInicioInvest;
    }

    public void setSelectedInicioInvest(List<Long> selectedInicioInvest) {
        this.selectedInicioInvest = selectedInicioInvest;
    }

    public List<Long> getSelectedDesaInvestig() {
        return selectedDesaInvestig;
    }

    public void setSelectedDesaInvestig(List<Long> selectedDesaInvestig) {
        this.selectedDesaInvestig = selectedDesaInvestig;
    }

    public List<Long> getSelectedDesaOperativ() {
        return selectedDesaOperativ;
    }

    public void setSelectedDesaOperativ(List<Long> selectedDesaOperativ) {
        this.selectedDesaOperativ = selectedDesaOperativ;
    }

    public List<Long> getSelectedResultInv() {
        return selectedResultInv;
    }

    public void setSelectedResultInv(List<Long> selectedResultInv) {
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

    public List<Long> getSelectedActProduct() {
        return selectedActProduct;
    }

    public void setSelectedActProduct(List<Long> selectedActProduct) {
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

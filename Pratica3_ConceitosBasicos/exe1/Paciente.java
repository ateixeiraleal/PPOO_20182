package exe1;

public class Paciente {
    private String nome;
    private double[] exames;

    public Paciente(String nome, int qde) {
        this.nome = nome;
        exames = new double[qde];
    }
    
    public boolean inserirExame(double exame){
        for(int i = 0; i < exames.length; i++){
            if(exames[i] == 0){
                exames[i] = exame;
                return true;
            }
        }
        return false;
    }
    
    public String getPaciente(){
        return nome;
    }
    
    public double getExame(int posicao){
        return exames[posicao];
    }
    
    public double getMediaExames(){
        double media = 0;
        for(int i = 0; i < exames.length; i++){
            media += exames[i];
        }
        return media / exames.length;
    }
    
    public String getDiagnostico(){
        double resultado = getMediaExames();
        if(resultado < 140){
            return "Glicemia normal";
        }
        if(resultado <= 199){
            return "Risco de diabetes";
        }
        return "Diabetes Mellitus";
    }
}
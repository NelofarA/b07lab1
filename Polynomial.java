import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.PrintWriter;
public class Polynomial{
    double [] coeff;
    int [] exp;

    public Polynomial(){
        coeff = new double[] {0.0};
        exp = new int[] {0};
    }

public Polynomial(File file) throws IOException {
    Scanner input = new Scanner(file);
    String line = input.nextLine();
    input.close();
    if(line.charAt(0)!='+' && line.charAt(0) != '-'){
    line = "+" + line;
}
    int count=0;
    for(int i=0; i<line.length(); i++){
        if(line.charAt(i)== '+'|| line.charAt(i)== '-'){
        count++;
    }
    }
    coeff = new double[count];
    exp = new int[count];
    int termIndex=0;
    int start=0;
    for(int i = 1; i<line.length(); i++){
        if(line.charAt(i)== '+' ||line.charAt(i)== '-'){
            String term =line.substring(start, i);
            parseTerm(term, termIndex);
            termIndex++;
            start= i;
        }
    }
    parseTerm(line.substring(start), termIndex);//lastterm is seperatly
}
private void parseTerm(String term, int index){//helper
//term alwys has + or - in the front
    char sign=term.charAt(0);
    String body=term.substring(1);
    double coeffValue;
    int expValue;
    int xIndex = body.indexOf('x');
    if(xIndex == -1){
        coeffValue=Double.parseDouble(body);
        expValue=0;
    }
    else{
        //There is x
        String coeffPart = body.substring(0, xIndex);
        String expPart = body.substring(xIndex + 1);
        if(coeffPart.equals("")||coeffPart.equals("+")){
        coeffValue = 1.0;
        }
        else if(coeffPart.equals("-")){
        coeffValue = -1.0;
        }
        else{
         coeffValue=Double.parseDouble(coeffPart);
        }
        // Parse exponent
        if(expPart.equals("")){
        expValue = 1;
        }
        else{
        expValue = Integer.parseInt(expPart);
    }
    }
    if(sign== '-'){
    coeffValue = -coeffValue;
    }
    coeff[index] = coeffValue;
    exp[index] = expValue;
}
   public Polynomial(double [] coeff , int [] exp){
        this.coeff = new double[coeff.length];
        this.exp = new int[exp.length];
        for(int i = 0; i < coeff.length; i++){
            this.coeff[i]=coeff[i];
             this.exp[i] = exp[i];

        }
    }
    public Polynomial add(Polynomial polynomial){
        int len1 = this.coeff.length;
        int len2= polynomial.coeff.length;
        int maxlen= len1+len2;
   
        double[] resultCoeff = new double[maxlen];
        int[] resultExp = new int[maxlen];

        for(int i=0; i<len1; i++){
        resultCoeff[i] = this.coeff[i];
        resultExp[i] = this.exp[i];
        }
        int end = len1;
        for(int j=0; j<len2; j++){
        int added=0;
        for(int i=0; i<end; i++){
            if(resultExp[i] == polynomial.exp[j]){
                resultCoeff[i]+= polynomial.coeff[j]; 
                added = 1;
                break;
            }
        }
        if(added==0) {
            resultCoeff[end] = polynomial.coeff[j];
            resultExp[end] = polynomial.exp[j];
            end++;
        }
    }
    double[] finalCoeff = new double[end];
    int[] finalExp = new int[end];
    for(int i=0; i < end; i++){
        finalCoeff[i] =resultCoeff[i];
        finalExp[i] =resultExp[i];
    }
    return new Polynomial(finalCoeff, finalExp);
}
    


    
    public double evaluate(double x){
        double result=0;
        for(int i=0;i<this.coeff.length; i++){
            result+= this.coeff[i]* Math.pow(x,this.exp[i]);
        }
        return result;
    }
     public boolean hasRoot(double x){
        double val= evaluate(x);
        if (val==0){
            return true;

        }else{
            return false;
        }
     }


     public Polynomial multiply(Polynomial polynomial){
        int len1 = this.coeff.length;
        int len2= polynomial.coeff.length;
        int maxlen= len1*len2;
        double[] tempCoeff = new double[maxlen];
        int[] tempExp = new int[maxlen];
        int idx = 0;
   
        for(int i=0; i<len1; i++){
            for(int j=0; j<len2; j++){
            tempCoeff[idx] = this.coeff[i]*polynomial.coeff[j];
            tempExp[idx] = this.exp[i]+polynomial.exp[j];
            idx++;
        }
        }
    double[] combinedCoeff=new double[maxlen];
    int[] combinedExp= new int[maxlen];
    int combinedLen=0;
    for(int i=0; i<idx; i++){
        int found = 0;
        for (int k=0; k<combinedLen; k++){
            if(combinedExp[k] ==tempExp[i]){
                combinedCoeff[k] += tempCoeff[i];
                found = 1;
                break;
        }
        }
        if(found==0){
        combinedCoeff[combinedLen] = tempCoeff[i];
        combinedExp[combinedLen] = tempExp[i];
        combinedLen++;
        }
    }

    double[] finalCoeff =new double[combinedLen];
    int[] finalExp =new int[combinedLen];
    for(int i = 0; i < combinedLen; i++){
        finalCoeff[i] =combinedCoeff[i];
        finalExp[i] =combinedExp[i];
    }
    return new Polynomial(finalCoeff, finalExp);
}  



public void saveToFile(String filename) throws IOException {
    PrintWriter writer=new PrintWriter(filename);
    StringBuilder sb=new StringBuilder();
    for(int i=0; i<coeff.length; i++){
    double coefficient=coeff[i];
    int exponent =exp[i];
    if(i ==0){
     if(coefficient <0){
        sb.append("-");
    }
     } 
    else{
    if(coefficient <0){
        sb.append("-");
    } 
    else{
     sb.append("+");
        }
        }
    double absCoeff= Math.abs(coefficient);
    if(exponent ==0){
    sb.append(absCoeff);
    } 
    else if(exponent == 1){
        if(absCoeff == 1.0){
            sb.append("x");
        }
        else{
        sb.append(absCoeff);
        sb.append("x");
        }
        }
        else{
        if(absCoeff ==1.0){
            sb.append("x");
            sb.append(exponent);
        }
        else{
        sb.append(absCoeff);
        sb.append("x");
        sb.append(exponent);
    }
        }
    }

    writer.println(sb.toString());
    writer.close();
}
}
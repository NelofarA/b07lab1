public class Polynomial{
    double [] coeff;

    public Polynomial(){
        coeff = new double[] {0.0};
    }
    public Polynomial(double [] myArray ){
        coeff = new double[myArray.length];
        for(int i = 0; i < myArray.length; i++){
            coeff[i]=myArray[i];
        }
    }
    public Polynomial add(Polynomial polynomial){
        int len1 = this.coeff.length;
        int len2= polynomial.coeff.length;
        int maxlen= len1;
        if (len2>len1){
            maxlen= len2;
        }

        double[] result = new double[maxlen];

        for(int i=0; i<maxlen; i++){
            double val1=0;
            double val2=0;
            if(i<len1){
                val1=this.coeff[i];
            }
            if(i<len2){
                val2=polynomial.coeff[i];
            }
            result[i]=val1+val2;
        }
        return new Polynomial(result);
    }
    public double evaluate(double x){
        double result=0;
        for(int i=0;i<this.coeff.length; i++){
            result+= this.coeff[i]*Math.pow(x,i);
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









  
}
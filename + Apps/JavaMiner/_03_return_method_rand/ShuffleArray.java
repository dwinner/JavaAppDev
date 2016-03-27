class ShuffleArray{
    
    public static final int MR = 10;
    public static final int MC = 10;
    public int status = 0; // статус начала игры
    public int nMin = 0; // нет обнаруженных мин
    public int nFlag = 0; // флаг не поставлен
    public static int row,col;
    public int[][] Pole;
    
    public void RandomArray(){
             
        Pole  = new int[MR+2][MC+2]; 

        for(row = 0; row <= MR+1; row++)
            for(col = 0; col <= MC+1; col++)
                Pole[row][col] = -3;		
        
        for(row = 1; row <= MR; row++)
            for(col = 1; col <= MC; col++)
                Pole[row][col] = 0;
	
	int n = 0; // количество мин
	M1:
	for(row = 1; row <= MR; row++){
	    for(col = 1; col <= MC; col++){
		double rand = Math.random(); // генерируем случайное число    
		Pole[row][col] = (int)(10*rand % MR);
		if(Pole[row][col] == 9){
			n++;
		}
		if(n == 10) break M1;
	    }
	}

        // вычислим количество мин в соседних клетках
        int k;
        for(row = 1; row <= MR; row++)
            for(col = 1; col <= MC; col++)
                if(Pole[row][col] != 9){
                    k = 0;
                    if(Pole[row-1][col-1] == 9) k++;
                    if(Pole[row-1][col] == 9) k++;
                    if(Pole[row-1][col+1] == 9) k++;
                    if(Pole[row][col-1] == 9) k++;
                    if(Pole[row][col+1] == 9) k++;
                    if(Pole[row+1][col-1] == 9) k++;
                    if(Pole[row+1][col] == 9) k++;
                    if(Pole[row+1][col+1] == 9) k++;
                    Pole[row][col] = k;
                }
    }
    
    public static void main(String[] args){
        
        // вывод массива
        ShuffleArray arr = new ShuffleArray();
        arr.RandomArray();
        for(row = 0; row <= MR+1; row++){
           for(col = 0; col <= MC+1; col++){
                System.out.print(arr.Pole[row][col]+" ");
           }
           System.out.println();
        }
    }
    
}
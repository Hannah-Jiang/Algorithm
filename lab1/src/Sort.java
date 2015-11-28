package lab1;

import java.util.Date;

public class Sort {
    
    //================================= sort =================================
    //
    // Input: array A of XYPoints refs 
    //        lessThan is the function used to compare points
    //
    // Output: Upon completion, array A is sorted in nondecreasing order
    //         by lessThan.
    //
    // DO NOT USE ARRAYS.SORT.  WE WILL CHECK FOR THIS.  YOU WILL GET A 0.
    // I HAVE GREP AND I AM NOT AFRAID TO USE IT.
    //=========================================================================
    public static void msort(XYPoint[] A, Comparator lessThan) {
         devide(0,A.length-1,A,lessThan);
    }
    
    private static void devide(int istart, int iend, XYPoint[] A, Comparator lessThan){
        if(iend -istart >=1){
                int imiddle = istart + (iend - istart) / 2;
                devide(istart, imiddle,A,lessThan);
                devide(imiddle+1, iend,A,lessThan);
                merge(istart,imiddle,iend,A,lessThan);
        }
        
    }
    
    private static void merge(int istart, int imiddle, int iend, XYPoint[] A, Comparator lessThan){
        XYPoint[] B = new XYPoint[iend-istart+1];
        copy(A,B,istart,iend);
		
        int i = istart;
        int j = imiddle+1;
        int k = istart;
        while (i <= imiddle && j <= iend) {
            if (lessThan.comp(B[i-istart],B[j-istart])) {
            	A[k] = B[i-istart];
                i++;
            } else {
            	A[k] = B[j-istart];
                j++;
            }
            k++;
        }
        
        while (i <= imiddle) {
        	A[k] = B[i-istart];
            k++;
            i++;
        }
        
        
    }

    private static void copy(XYPoint[] A, XYPoint[] B, int istart, int iend){
    	int k = 0;
    	for(int i = istart; i <= iend; i++){
            B[k++] = A[i];
    	}
    }
}

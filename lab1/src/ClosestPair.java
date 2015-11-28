package lab1;

import java.util.ArrayList;

public class ClosestPair {

    public final static double INF = java.lang.Double.POSITIVE_INFINITY;


    /** 
     * Given a collection of points, find the closest pair of point and the
     * distance between them in the form "(x1, y1) (x2, y2) distance"
     *
     * @param pointsByX points sorted in nondecreasing order by X coordinate
     * @param pointsByY points sorted in nondecreasing order by Y coordinate
     * @return Result object containing the points and distance
     */
    static Result findClosestPair(XYPoint pointsByX[], XYPoint pointsByY[]) {
       // YOUR CODE GOES HERE
    	int len = pointsByX.length;
    	if(len == 0){
    		XYPoint a = null;
    		XYPoint b = null;
    		Result res = new Result(a,b, INF);
    		return res;
    	}
        Result finalResult = null;
        finalResult = conquer(pointsByX, pointsByY, 0, len-1, finalResult);
    	XYPoint p1 = finalResult.p1;
    	XYPoint p2 = finalResult.p2;
    	if(p1.x < p2.x || p1.x == p2.x && p1.y < p2.y){
    		System.out.println(p1 + "  " + p2);
    		return finalResult;
    	}else{
    		Result res = new Result(p2,p1,finalResult.dist);
    		System.out.println(p2 + "  " + p1);
    		return res;
    	}
    }
    
    static int abs(int x) {
        if (x < 0) {
            return -x;
        } else {
            return x;
        }
    }
    
    private static Result conquer(XYPoint pointsByX[], XYPoint pointsByY[], int start,int end, Result finalResult){
    	if(start == end){
    		Result result = new Result(pointsByX[0], pointsByX[0], INF);
    		return result;
    	}
    	if (start+1 == end){
    		Result result = getDistance(pointsByX,pointsByY,start,start+1);
    		return result;
    	}

    	int middle = start + (end - start) / 2;
    	Result rl = conquer(pointsByX,pointsByY,start, middle,finalResult);
    	Result rr = conquer(pointsByX,pointsByY,middle+1, end, finalResult);
    	double dl = rl.dist;
    	double dr = rr.dist;
    	Result tempResult;
    	if (dl < dr){
    		tempResult = rl;
    	}else if(dl == dr){
    		if(rl.p1.x < rr.p1.x || rl.p1.x == rr.p1.x && rl.p1.y < rr.p1.y){
    			tempResult = rl;
			}else{
				tempResult = rr;
			}
    	}else{
    		tempResult = rr;
    	}
    	
    	if(finalResult == null || tempResult.dist < finalResult.dist){
    		finalResult = tempResult;
    	}else if(tempResult.dist == finalResult.dist && tempResult.p1.x < finalResult.p1.x){
    		finalResult = tempResult;
    	}else if(tempResult.dist == finalResult.dist && tempResult.p1.x == finalResult.p1.x && tempResult.p1.y < finalResult.p1.y){
    		finalResult = tempResult;
    	}
    	
    	int l=middle;
    	int r = middle+1;
    	while(l>start){
    		if(abs(pointsByX[l].x - pointsByX[middle].x) <= finalResult.dist){
    			l--;
    		}else{
    			l++;
    			break;
    		}
    	}
    	while(r<end){
    		if(abs(pointsByX[r].x - pointsByX[middle].x) <= finalResult.dist){
    			r++;
    		}else{
    			r++;
    			break;
    		}
    	}
    	if(l!=r){
    		for(int a = l;a<=middle;a++){
    			for(int b = middle+1;b<=r;b++){
    				Result mresult = getDistance(pointsByX,pointsByY,a,b);
    				double dm = mresult.dist;
    				if(dm < finalResult.dist){
    					finalResult = mresult;
    				}
    			}
    		}
    	}
    	return finalResult;
    	
  	}
    
    private static Result getDistance(XYPoint pointsByX[], XYPoint pointsByY[], int start, int end){
    	double dx = Math.sqrt(Math.pow((pointsByX[start].x - pointsByX[end].x),2) + Math.pow((pointsByX[start].y - pointsByX[end].y),2));
    	Result result = new Result(pointsByX[start], pointsByX[end], dx);
		return result;
    }
}

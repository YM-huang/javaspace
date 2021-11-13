package bigint;
//
//import java.math.BigInteger;
//
public class Toom_cook_3 {

//	private static BigInteger multiplyToomCook3(BigInteger a, BigInteger b) {
//	    int alen = a.toString().length();
//	    int blen = b.toString().length();
//
//	    int largest = Math.max(alen, blen);
//
//	    // k is the size (in ints) of the lower-order slices.
//	    int k = (largest+2)/3;   // Equal to ceil(largest/3)
//
//	    // r is the size (in ints) of the highest-order slice.
//	    int r = largest - 2*k;
//
//	    // Obtain slices of the numbers. a2 and b2 are the most significant
//	    // bits of the numbers a and b, and a0 and b0 the least significant.
//	    BigInteger a0, a1, a2, b0, b1, b2;
//	    
//	    a2 = a.getToomSlice(k, r, 0, largest);
//	    a1 = a.getToomSlice(k, r, 1, largest);
//	    a0 = a.getToomSlice(k, r, 2, largest);
//	    b2 = b.getToomSlice(k, r, 0, largest);
//	    b1 = b.getToomSlice(k, r, 1, largest);
//	    b0 = b.getToomSlice(k, r, 2, largest);
//
//	    BigInteger v0, v1, v2, vm1, vinf, t1, t2, tm1, da1, db1;
//
//	    v0 = a0.multiply(b0);
//	    da1 = a2.add(a0);
//	    db1 = b2.add(b0);
//	    vm1 = da1.subtract(a1).multiply(db1.subtract(b1));
//	    da1 = da1.add(a1);
//	    db1 = db1.add(b1);
//	    v1 = da1.multiply(db1);
//	    v2 = da1.add(a2).shiftLeft(1).subtract(a0).multiply(
//	         db1.add(b2).shiftLeft(1).subtract(b0));
//	    vinf = a2.multiply(b2);
//
//	    // The algorithm requires two divisions by 2 and one by 3.
//	    // All divisions are known to be exact, that is, they do not produce
//	    // remainders, and all results are positive.  The divisions by 2 are
//	    // implemented as right shifts which are relatively efficient, leaving
//	    // only an exact division by 3, which is done by a specialized
//	    // linear-time algorithm.
//	    t2 = v2.subtract(vm1).exactDivideBy3();
//	    tm1 = v1.subtract(vm1).shiftRight(1);
//	    t1 = v1.subtract(v0);
//	    t2 = t2.subtract(t1).shiftRight(1);
//	    t1 = t1.subtract(tm1).subtract(vinf);
//	    t2 = t2.subtract(vinf.shiftLeft(1));
//	    tm1 = tm1.subtract(t2);
//
//	    // Number of bits to shift left.
//	    int ss = k*32;
//
//	    BigInteger result = vinf.shiftLeft(ss).add(t2).shiftLeft(ss).add(t1).shiftLeft(ss).add(tm1).shiftLeft(ss).add(v0);
//
//	    if (a.signum() != b.signum()) {
//	        return result.negate();
//	    } else {
//	        return result;
//	    }
//	}
//	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		BigInteger a;
//		System.out.println(a.multiplyToomCook3(new BigInteger("101254645656454"), new BigInteger("101254645656453")));
	}

}
//
//class BigInteger{
//	final int[] mag;
//	private BigInteger getToomSlice(int lowerSize, int upperSize, int slice,int fullsize) {
//		int start, end, sliceSize, len, offset;
//
//		len = mag.length;
//		offset = fullsize - len;
//		
//		if (slice == 0) {
//			start = 0 - offset;
//			end = upperSize - 1 - offset;
//		} else {
//			start = upperSize + (slice-1)*lowerSize - offset;
//			end = start + lowerSize - 1;
//		}
//	
//		if (start < 0) {
//			start = 0;
//		}
//		if (end < 0) {
//			return ZERO;
//		}
//		
//		sliceSize = (end-start) + 1;
//		
//		if (sliceSize <= 0) {
//			return ZERO;
//		}
//		
//		// While performing Toom-Cook, all slices are positive and
//		// the sign is adjusted when the final number is composed.
//		if (start == 0 && sliceSize >= len) {
//			return this.abs();
//		}
//		
//		int intSlice[] = new int[sliceSize];
//		System.arraycopy(mag, start, intSlice, 0, sliceSize);
//		
//		return new BigInteger(trustedStripLeadingZeroInts(intSlice), 1);
//	}
//}



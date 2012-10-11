/**
 * 
 */
package test;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser.POINT;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.StdCallLibrary;

/**
 * @author Abi
 *
 */
public class Test{
	
	public interface User32 extends StdCallLibrary {
	      User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
	      boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);
	      int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);	 
	      boolean SetForegroundWindow(WinDef.HWND hWnd);
	      boolean CloseWindow(WinDef.HWND hWnd);
	      WinDef.HWND GetWindow(WinDef.HWND hWnd,
                  WinDef.DWORD uCmd);
	   }

	   public static void main(String[] args) {
	      final User32 user32 = User32.INSTANCE;	     
	      user32.EnumWindows(new WNDENUMPROC() {
	         int count = 0;
	         
	         public boolean callback(HWND hWnd, Pointer arg1) {
	            byte[] windowText = new byte[512];
	            user32.GetWindowTextA(hWnd, windowText, 1000);
	            String wText = Native.toString(windowText);
	            System.out.println("Found window with text " + hWnd + ", total " + ++count
	                  + " Text: " + wText);
	            if(wText.contains("Program Manager")){
	            	WinDef.HWND h = user32.GetWindow(hWnd, new WinDef.DWORD());
	            	Pointer n = h.getPointer();
	            	n.getString(0);
	            }
	            return true;
	         }
	      }, null);
	   }


}

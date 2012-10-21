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
	
	/**
	 * I implement the User32 interface and by writing the method names on the interface call I can call the methods 
	 * @author Abi
	 *
	 */
	public interface User32 extends StdCallLibrary {
	      User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class); //I load the instance of the user32 onto the program
	      /*
	       * The methods I would like to use in my program
	       */
	      boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);
	      int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);	 
	      boolean SetForegroundWindow(WinDef.HWND hWnd);
	      boolean CloseWindow(WinDef.HWND hWnd);
	      WinDef.HWND GetWindow(WinDef.HWND hWnd,
                  WinDef.DWORD uCmd);
	   }

	/**
	 * I parse through the list of windows which are open in Windows and select the program with the name 
	 * "Program Manager" because that is the name of the windows desktop manager
	 * @param args
	 */
	   public static void main(String[] args) {
	      final User32 user32 = User32.INSTANCE; //I get the instance i loaded above
	      user32.EnumWindows(new WNDENUMPROC() {
	         int count = 0;
	         
	         public boolean callback(HWND hWnd, Pointer arg1) {
	            byte[] windowText = new byte[512];
	            user32.GetWindowTextA(hWnd, windowText, 1000);//I get all windows
	            String wText = Native.toString(windowText); //Get the name of the window
	            System.out.println("Found window with text " + hWnd + ", total " + ++count
	                  + " Text: " + wText);
	            if(wText.contains("Program Manager")){ //Get and save the window manager onto an instance
	            	WinDef.HWND h = user32.GetWindow(hWnd, new WinDef.DWORD());
	            	Pointer n = h.getPointer();
	            	n.getString(0);
	            }
	            return true;
	         }
	      }, null);
	   }


}

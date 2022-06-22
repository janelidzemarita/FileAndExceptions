import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {

		File currentDirectory = new File(args[0]);
		ReverseFileBuffered(); // Task 3
		ReverseFileNotBuffered();
		
		// Task 1.
		if (currentDirectory.isDirectory() && currentDirectory.exists()) {
			String[] files = currentDirectory.list();

			for (int i = 0; i < files.length; i++) {
				File directoryItems = new File(currentDirectory, files[i]);

				if (directoryItems.isDirectory()) {
					System.out.println(directoryItems.getName() + " - Directory");
					String[] filesInFiles = directoryItems.list();
					if (filesInFiles != null) {
						for (int j = 0; j < filesInFiles.length; j++) {
							File directoryItemsAgain = new File(directoryItems, filesInFiles[j]);
							if (directoryItemsAgain.isDirectory()) {
								System.out.println("In " + directoryItems.getName() + " | "
										+ directoryItemsAgain.getName() + " - Directory");
								/*
								 * Quote from task 1 მასში არსებულ ყველა ფაილისა და ფოლდერის სახელს
								 * 
								 * ყველას მისამართი უნდა დაიბეჭდოს
								 * 
								 * Here I used .getName to print the name of files and directories We can change
								 * it to .getAbsolutePath() or .getPath() or .getCanonicalPath() to print the
								 * path. same logic.
								 */
							}
							if (directoryItemsAgain.isFile()) {
								System.out.println("In " + directoryItems.getName() + " | "
										+ directoryItemsAgain.getName() + " - File");
							}
							System.out.print(" ");
						}
					}
				}

				if (directoryItems.isFile()) {
					System.out.println(directoryItems.getName() + " - File");
				}
				System.out.print(" ");
			}
			System.out.println("Taks 2 starts here!");
			// Task 2
			for (int i = 0; i < files.length; i++) {
				File dirItems = new File(currentDirectory, files[i]);
				if (dirItems.isFile()) {
					String extention = getFileExtension(dirItems.getName());
					if ("exe".equals(extention)) {
						System.out.println(dirItems.getName());
					}
				}
			}
		}
		BufferedInputStream s = new BufferedInputStream(new FileInputStream("D:\\TBC_Academy\\FileAndExceptions\\src\\ReadMyFile"));
		CopyToFile(s);
	}

	// helper for Task 2
	public static String getFileExtension(String fullName) {
		if (fullName != null) {
			String fileName = new File(fullName).getName();
			int dotIndex = fileName.lastIndexOf('.');
			return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
		}
		return " ";
	}

	// Task 3 and 5 Call this function to get result
	public static void ReverseFileBuffered() {
		InputStream fis = null;
		OutputStream fos = null;
		try {
			 fis = new BufferedInputStream(new FileInputStream("D:\\TBC_Academy\\FileAndExceptions\\src\\ReadMyFile"));
			byte[] bytes = fis.readAllBytes();
			byte[] revBytes = new byte[bytes.length];

			for (int i = 0; i < bytes.length; i++) {
				revBytes[bytes.length - 1 - i] = bytes[i];
			}
			fos = new BufferedOutputStream( new FileOutputStream("D:\\TBC_Academy\\FileAndExceptions\\src\\RevFileText"));
			fos.write(revBytes);
			fos.toString();
		}catch(FileNotFoundException e) {
			System.out.println("File not found: " + e);
		}catch (IOException ioe) {
            System.out.println("I/O Exception: " + ioe);
        }finally {
        	if(fis != null && fos != null) {
        		try { 
        			fis.close();
        			fos.close();
        		} catch (IOException e) {}
        	}
        	
        }
	}
	public static void ReverseFileNotBuffered() throws IOException {
		InputStream fis = null;
		OutputStream fos = null;
		try {
			fis = new FileInputStream("D:\\TBC_Academy\\FileAndExceptions\\src\\ReadMyFile");
			byte[] bytes = fis.readAllBytes();
			byte[] revBytes = new byte[bytes.length];

			for (int i = 0; i < bytes.length; i++) {
				revBytes[bytes.length - 1 - i] = bytes[i];
			}
			
			fos = new FileOutputStream("D:\\TBC_Academy\\FileAndExceptions\\src\\RevFileText");
			fos.write(revBytes);
			fos.toString();
		}catch(FileNotFoundException e) {
			System.out.println("File not found: " + e);
		}catch (IOException ioe) {
            System.out.println("I/O Exception: " + ioe);
        }finally {
        	if(fis != null && fos != null) {
        		try { 
        			fis.close();
        			fos.close();
        		} catch (IOException e) {}
        	}
        	
        }
	}
	/*
	 6.  დაწერეთ პროგრამა, რომელიც საწყისი შემავალი ფაილიდან ამოიკითხავს ბაიტებს
	 და ამ ინფორმაციას გადააკოპირებს ახალ გამომავალ ფაილში. 
	 ანუ პროგრამის  დასრულების შემდეგ შემავალი ფაილის შიგთავსი უნდა 
	 გადაკოპირდეს გამომავალ  ფაილის შიგთავსში. პროცესი აუცილებლად უნდა იყოს ბუფერიზებული 
	 და მყარ  დისკზე გადატანა გამომავალ ფაილში უნდა ხდებოდეს შემავალი ფაილიდან ყოველი 3  ბაიტის ამოკითხვის შემდეგ. 
	 ანუ ყოველი 3 ბაიტის ამოკითხვის შემდეგ  ინფორმაცია ბუფერიდან უნდა გადადიოდეს გამომავალ ფაილში მყარ დისკზე.
	 */
	public static void CopyToFile(BufferedInputStream fis) {
		OutputStream fos = null;
		try {
			byte[] bytes = new byte[3];
			fos = new BufferedOutputStream(new FileOutputStream("D:\\TBC_Academy\\FileAndExceptions\\src\\CopiedText"));
			while(fis.read()!= -1) {
				fis.read(bytes);
				fos.write(bytes);
			}			
			fos.toString();
		}catch(FileNotFoundException e) {
			System.out.println("File not found: " + e);
		}catch (IOException ioe) {
           System.out.println("I/O Exception: " + ioe);
       }finally {
       	if(fis != null && fos != null) {
       		try { 
       			fis.close();
       			fos.close();
       		} catch (IOException e) {}
       	}
       	
       }
	}
}

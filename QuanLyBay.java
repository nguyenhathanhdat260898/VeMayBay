package Homework12;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuanLyBay {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		List<VeMayBay> list = new ArrayList<>();
		int choose;
		do {
			Menu();
			choose = input.nextInt();
			switch (choose) {
			case 1:
				input(list);
				break;
			case 2:
				save(list, "DuLieuBay.dat");
				break;
			case 3:
				read(list, "DuLieuBay.dat");
				break;
			case 4:
				sort(list);
				break;
			case 5:
				saveByAirline(list);
				break;
			case 6:
				System.out.println("GoodBye");
				break;
			default:
				System.out.println("Input fail");
			}
		} while (choose != 6);
	}

	static void input(List<VeMayBay> list) {
		System.out.println("Nhap n chuyen bay");
		int n = input.nextInt();
		for (int i = 0; i < n; i++) {
			VeMayBay veMayBay = new VeMayBay();
			veMayBay.input();
		}
	}

	static void save(List<VeMayBay> list, String fileName) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	static void sort(List<VeMayBay> list) {
		Collections.sort(list, (o1, o2) -> {
			// TODO Auto-generated method stub
			if (o1.getPrice() < o2.getPrice()) {
				return -1;
			}
			return 1;
		});
		for (int i = 0; i < list.size(); i++) {
			list.get(i).display();
		}
	}

	static void saveByAirline(List<VeMayBay> list) {
		List<VeMayBay> vietJetAirline = getAirLine(list, "VJ");
		save(vietJetAirline, "VietJetAirline.dat");
		List<VeMayBay> VNAirlineList = getAirLine(list, "VN");
		save(VNAirlineList, "VNAirline.dat");
		List<VeMayBay> jetstartList = getAirLine(list, "JET");
		save(jetstartList, "JETAirline.dat");
	}

	static List<VeMayBay> getAirLine(List<VeMayBay> list, String prex) {
		List<VeMayBay> airLineList = new ArrayList<>();
		for (VeMayBay veMayBay : list) {
			if (veMayBay.getId().startsWith(prex)) {
				airLineList.add(veMayBay);
			}
		}
		return airLineList;

	}

	@SuppressWarnings("unchecked")
	static void read(List<VeMayBay> list, String filename) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			try {
				list = (List<VeMayBay>) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	static void Menu() {
		System.out.println("1. Nhap thong tin cho N chuyen bay");
		System.out.println("2. Luu thong tin vao file ");
		System.out.println("3. Doc thong tin tu file va hien thi");
		System.out.println("4. Hien thi thong tin sau khi Sort ");
		System.out.println("5. In thong tin moi hang bay ra file ");
		System.out.println("6. Exit");
		System.out.println("Choose :");
	}
}

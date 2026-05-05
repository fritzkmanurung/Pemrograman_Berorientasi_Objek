package pbo;

/**
 * /**
 * @author
 * // 12S21014 - Fritz Kevin Manurung
 * // 12S21060 - Glory Natasya Hutahaean
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Mahasiswa {
    private String nim;
    private String nama;
    private String prodi;

    public Mahasiswa(String nim, String nama, String prodi) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
    }

    public String getNim() {
        return nim;
    }

    public String getNama() {
        return nama;
    }

    public String getProdi() {
        return prodi;
    }
}

class MataKuliah {
    private String kode;
    private String nama;
    private int semester;
    private int kredit;

    public MataKuliah(String kode, String nama, int semester, int kredit) {
        this.kode = kode;
        this.nama = nama;
        this.semester = semester;
        this.kredit = kredit;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getSemester() {
        return semester;
    }

    public int getKredit() {
        return kredit;
    }
}

public class App {
    private static List<Mahasiswa> basisDataMahasiswa = new ArrayList<>();
    private static List<MataKuliah> basisDataMataKuliah = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String perintah = scanner.nextLine();

            if (perintah.startsWith("student-add")) {
                String[] dataMahasiswa = perintah.split("#");
                if (dataMahasiswa.length == 4) {
                    String nim = dataMahasiswa[1];
                    String nama = dataMahasiswa[2];
                    String prodi = dataMahasiswa[3];

                    Mahasiswa mahasiswa = new Mahasiswa(nim, nama, prodi);
                    basisDataMahasiswa.add(mahasiswa);
                } else {
                }
            } else if (perintah.startsWith("course-add")) {
                String[] dataMataKuliah = perintah.split("#");
                if (dataMataKuliah.length == 5) {
                    String kode = dataMataKuliah[1];
                    String nama = dataMataKuliah[2];
                    int semester = Integer.parseInt(dataMataKuliah[3]);
                    int kredit = Integer.parseInt(dataMataKuliah[4]);

                    MataKuliah mataKuliah = new MataKuliah(kode, nama, semester, kredit);
                    basisDataMataKuliah.add(mataKuliah);
                } else {
                }
            } else if (perintah.equals("student-show-all")) {
                Collections.sort(basisDataMahasiswa, (m1, m2) -> m1.getNim().compareTo(m2.getNim()));

                for (Mahasiswa mahasiswa : basisDataMahasiswa) {
                    System.out.println(mahasiswa.getNim() + "|" + mahasiswa.getNama() + "|" + mahasiswa.getProdi());
                }
            } else if (perintah.equals("course-show-all")) {
                Collections.sort(basisDataMataKuliah, (m1, m2) -> {
                    if (m1.getSemester() == m2.getSemester()) {
                        return m1.getKode().compareTo(m2.getKode());
                    } else {
                        return Integer.compare(m1.getSemester(), m2.getSemester());
                    }
                });

                for (MataKuliah mataKuliah : basisDataMataKuliah) {
                    System.out.println(
                            mataKuliah.getKode() + "|" +
                            mataKuliah.getNama() + "|" +
                            mataKuliah.getSemester() + "|" +
                            mataKuliah.getKredit()
                    );
                }
            } else if (perintah.equals("---")) {
                break;
            } else {
            }
        }
    }
}
 
 
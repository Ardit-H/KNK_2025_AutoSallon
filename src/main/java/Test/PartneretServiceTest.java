package Test;

import models.dto.Partneret.Partneret;
import services.PartneretService;

public class PartneretServiceTest {
    public static void main(String[] args) {
        PartneretService partneretService = new PartneretService();

        try {
            // Testo getById
            Partneret partneri = partneretService.getById(3);
            System.out.println("Emri i Kompanisë: " + partneri.getEmriKompanise());

            // Testo getAll
//            List<Partneret> lista = partneretService.getAll();
//            for (Partneret p : lista) {
//                System.out.println("ID: " + p.getPid());
//                System.out.println("Emri Kompanisë: " + p.getEmriKompanise());
//                System.out.println("Lloji Partnerit: " + p.getLlojiPartnerit());
//                System.out.println("Email: " + p.getEmail());
//                System.out.println("Telefoni: " + p.getTelefoni());
//                System.out.println("Adresa: " + p.getAdresa());
//                System.out.println("------------------------------");
//            }

            // Testo create
//            CreatePartneretDto createDto = new CreatePartneretDto(
//                    "Tech Solutions", "Servis", "tech@example.com", "+38349111222", "Prishtinë - Rruga B"
//            );
//            Partneret iRi = partneretService.create(createDto);
//            System.out.println("Partneri u shtua me ID: " + iRi.getPid());

            // Testo update
//            UpdatePartneretDto updateDto = new UpdatePartneretDto();
//            updateDto.setId(3);
//            updateDto.setEmail("kontakt@techsolutions.com");
//            updateDto.setTelefoni("+38344444888");
//            updateDto.setAdresa("Prishtinë - Lagjja e Re");
//            Partneret updated = partneretService.update(updateDto);
//            System.out.println("Partneri u përditësua: " + updated.getEmail());

            // Testo delete
            partneretService.delete(5);
            System.out.println("Partneri u fshi me sukses!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
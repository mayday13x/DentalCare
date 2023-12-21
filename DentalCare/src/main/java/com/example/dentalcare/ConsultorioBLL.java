package com.example.dentalcare;

import java.util.List;

public class ConsultorioBLL {

    public static void AdicionarConsultorio(Empresa empresa, List<Consultorio> consultorio){
        Repositorio.getRepositorio().getConsultorios().put(empresa, consultorio);
        System.out.println("O consultorio foi criado");
        Repositorio.getRepositorio().serializar("DentalCare\\src\\main\\resources\\repo\\repositorio.dat");
    }

    public static void AdiconarConsultorioEmp(Empresa empresa,Consultorio consultorio){
        Repositorio.getRepositorio().getEmpresas().get(empresa.getDono()).addConsultorio(consultorio);
    }
}

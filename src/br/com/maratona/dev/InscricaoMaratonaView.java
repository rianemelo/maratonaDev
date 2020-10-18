package br.com.maratona.dev;

//aqui usamos apenas o servlet(o que se h� de mias b�sico para java web).
//precisamos da depend�ncia javax.servlet-api. 

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inscricao")
public class InscricaoMaratonaView extends HttpServlet { 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //essa � uma checked exception, deve ser tratada for�osamente
		System.out.println("Teste Maratona!");
		PrintWriter out = resp.getWriter(); //classe respons�vel por gerar a resposta
		out.print("Maratona usando servlet...");
	}
	
}

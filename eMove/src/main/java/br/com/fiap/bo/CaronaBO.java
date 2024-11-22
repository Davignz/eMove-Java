package br.com.fiap.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.fiap.beans.Carona;
import br.com.fiap.dao.CaronaDAO;

public class CaronaBO {

    private CaronaDAO caronaDAO;

    public CaronaBO() {
        caronaDAO = new CaronaDAO();
    }

	public void criarCarona(Carona carona) throws SQLException {
        caronaDAO.criarCarona(carona);
    }

    public Carona getCaronaById(int id) throws SQLException {
        return caronaDAO.getCaronaById(id);
    }

    public List<Carona> listarCaronas() throws SQLException {
        return caronaDAO.listarCaronas();
    }
}

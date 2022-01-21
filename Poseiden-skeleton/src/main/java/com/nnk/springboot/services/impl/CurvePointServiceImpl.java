package com.nnk.springboot.services.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.ICurvePointService;

/**
 * Service CurvePointService respectant le contrat dénifi par ICurvePointService.
 */
@Service
public class CurvePointServiceImpl implements ICurvePointService {

	@Autowired
	private CurvePointRepository curvePointRepository;

	/**
	 * Setter de CurvePointRepository. 
	 * 
	 * @param CurvePointRepository
	 * @return void
	 */
	public void setCurvePointRepository(CurvePointRepository curvePointRepository) {
		this.curvePointRepository = curvePointRepository;
	}

	/**
	 * Méthode définie par ICurvePointService pour lister de tous les objets 'CurvePoint' dans la base de donnée.
	 * 
	 * @return List<CurvePoint>
	 */
	@Override
	public List<CurvePoint> findAll() {
		return curvePointRepository.findAll();
	}

	/**
	 * Méthode définie par ICurvePointService pour ajouter un objet 'CurvePoint' dans la base de donnée.
	 * 
	 * @param CurvePoint
	 * @return @Valid CurvePoint
	 */
	@Override
	public @Valid CurvePoint save(@Valid CurvePoint curvePoint) {
		return curvePointRepository.save(curvePoint);
	}

	/**
	 * Méthode définie par ICurvePointService pour obtenir un objet 'CurvePoint' à partir de sa clé primaire 'id' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return CurvePoint
	 */
	@Override
	public CurvePoint findById(Integer id) {
		return curvePointRepository.findById(id).get();
	}

	/**
	 * Méthode définie par ICurvePointService pour supprimer une objet 'CurvePoint' dans la base de donnée.
	 * 
	 * @param Integer
	 * @return CurvePoint
	 */
	@Override
	public CurvePoint deleteById(Integer id) {
		CurvePoint result = curvePointRepository.findById(id).get();
		curvePointRepository.deleteById(id);
		return result;
	}

}

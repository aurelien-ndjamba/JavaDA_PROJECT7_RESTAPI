package com.nnk.springboot.services;

import java.util.List;

import javax.validation.Valid;

import com.nnk.springboot.domain.CurvePoint;

/**
 * Interface ICurvePointService
 */
public interface ICurvePointService {

	/**
	 * Méthode pour lister tous les objets 'CurvePoint' dans la base de donnée
	 * 
	 * @return List<CurvePoint>
	 */
	public List<CurvePoint> findAll();

	/**
	 * Méthode pour ajouter un objet 'CurvePoint' dans la base de donnée
	 * 
	 * @param CurvePoint
	 * @return @Valid CurvePoint
	 */
	public @Valid CurvePoint save(@Valid CurvePoint curvePoint);

	/**
	 * Méthode pour obtenir un objet 'BidList' à partir de sa clé primaire 'id' dans la base de donnée
	 * 
	 * @param Integer
	 * @return CurvePoint
	 */
	public CurvePoint findById(Integer id);

	/**
	 * Méthode pour supprimer un objet 'CurvePoint' dans la base de donnée
	 * 
	 * @param Integer
	 * @return CurvePoint
	 */
	public CurvePoint deleteById(Integer id);

}

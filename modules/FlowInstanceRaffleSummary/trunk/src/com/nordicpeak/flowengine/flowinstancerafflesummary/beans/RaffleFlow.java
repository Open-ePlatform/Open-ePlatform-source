package com.nordicpeak.flowengine.flowinstancerafflesummary.beans;

import java.io.Serializable;
import java.util.List;

import se.unlogic.standardutils.annotations.WebPopulate;
import se.unlogic.standardutils.dao.annotations.DAOManaged;
import se.unlogic.standardutils.dao.annotations.Key;
import se.unlogic.standardutils.dao.annotations.ManyToOne;
import se.unlogic.standardutils.dao.annotations.OneToMany;
import se.unlogic.standardutils.dao.annotations.SimplifiedRelation;
import se.unlogic.standardutils.dao.annotations.Table;
import se.unlogic.standardutils.xml.GeneratedElementable;
import se.unlogic.standardutils.xml.XMLElement;

@Table(name = "raffle_flows")
@XMLElement
public class RaffleFlow extends GeneratedElementable implements Serializable {

	private static final long serialVersionUID = 1192909882382723199L;
	
//	public static final Field ROUND_RELATION = ReflectionUtils.getField(RaffleFlow.class, "round");

	@DAOManaged(autoGenerated = true)
	@Key
	@XMLElement
	private Integer raffleFlowID;

	@DAOManaged(columnName = "flowID")
	@XMLElement
	@WebPopulate
	private Integer flowID;

	@DAOManaged(columnName = "roundID")
	@ManyToOne
	private RaffleRound round;

	@DAOManaged
	@XMLElement
	@WebPopulate
	private Integer raffledStatusID;

	@DAOManaged
	@OneToMany(autoAdd = true, autoGet = true, autoUpdate = true)
	@SimplifiedRelation(table = "raffle_flow_excluded_statuses", remoteValueColumnName = "statusID")
	@XMLElement(fixCase = true)
	@WebPopulate
	private List<Integer> excludedStatusIDs;

	public Integer getRaffleFlowID() {

		return raffleFlowID;
	}

	public void setRaffleFlowID(Integer roundID) {

		this.raffleFlowID = roundID;
	}

	public Integer getFlowID() {

		return flowID;
	}

	public void setFlowID(Integer flowID) {

		this.flowID = flowID;
	}

	public Integer getRaffledStatusID() {

		return raffledStatusID;
	}

	public void setRaffledStatusID(Integer raffledStatusID) {

		this.raffledStatusID = raffledStatusID;
	}

	public void setExcludedStatusIDs(List<Integer> excludedStatusIDs) {

		this.excludedStatusIDs = excludedStatusIDs;
	}

	public List<Integer> getExcludedStatusIDs() {

		return excludedStatusIDs;
	}

	public RaffleRound getRound() {

		return round;
	}

	public void setRound(RaffleRound raffleRound) {

		this.round = raffleRound;
	}

}

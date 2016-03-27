select /* Doc line */
   transfers.item_num AS item_code,
   transfers.uit_num AS legacy_item_code,
   transfers.item_desc AS item_name,
   transfers.transaction_quantity AS quantity,
   transfers.item_cost AS item_cost,
   transfers.material AS material_code,
   /* "" as material_name, */
   transfers.mark AS material_sort,
   transfers.fusion AS melt_code,
   transfers.phisicalProperties AS phisical_properties,
   transfers.chemicalProperties AS chemical_properties,
   transfers.unit_from_blank AS items_per_billet,
   transfers.zag_ovk AS outsource_item_code,
   /* "" as outsource_item_name, */
   transfers.ves_ovk AS outsource_item_weight,
   /* "" as finished_good_figure, */
   transfers.sertificate AS certificate,
   transfers.razm_det AS item_size
   /* "" as notes */
from APPS.XXTPA_MFG0027_MOVEMENT_V transfers
where movement_num = '000001';

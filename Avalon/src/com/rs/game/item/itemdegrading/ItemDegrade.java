package com.rs.game.item.itemdegrading;

import java.util.concurrent.TimeUnit;

import com.rs.game.item.Item;

/**
 * @author -Andreas 1 feb. 2020 13:58:09
 * @project 1. Avalon
 * 
 */

public class ItemDegrade {

	public enum DegradeType {

		WEAR,

		IN_COMBAT,

		AT_OUTGOING_HIT,

		AT_INCOMMING_HIT,
		
		HITS;
	}

	public enum ItemStore {

		/**
		 * @CorruptStatius
		 */

		CORRUPT_STATIUS_HELM(new Item(13920), new Item(13922), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_STATIUS_HELM_DEG(new Item(13922), null, new DegradeTime(TimeUnit.MINUTES, 15)),
		CORRUPT_STATIUS_BODY(new Item(13908), new Item(13910), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_STATIUS_BODY_DEG(new Item(13910), null, new DegradeTime(TimeUnit.MINUTES, 15)),
		CORRUPT_STATIUS_LEGS(new Item(13914), new Item(13916), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_STATIUS_LEGS_DEG(new Item(13916), null, new DegradeTime(TimeUnit.MINUTES, 15)),

		CORRUPT_STATIUS_WARHAMMER(new Item(13926), new Item(13928), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_STATIUS_WARHAMMER_DEG(new Item(13928), null, new DegradeTime(TimeUnit.MINUTES, 15)),

		/**
		 * @CorruptVesta
		 */

		CORRUPT_VESTA_LONGSWORD(new Item(13923), new Item(13925), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_VESTA_LONGSWORD_DEG(new Item(13925), null, new DegradeTime(TimeUnit.MINUTES, 15)),
		CORRUPT_VESTA_SPEAR(new Item(13929), new Item(13931), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_VESTA_SPEAR_DEG(new Item(13931), null, new DegradeTime(TimeUnit.MINUTES, 15)),

		CORRUPT_VESTA_BODY(new Item(13911), new Item(13913), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_VESTA_BODY_DEG(new Item(13913), null, new DegradeTime(TimeUnit.MINUTES, 15)),
		CORRUPT_VESTA_LEGS(new Item(13917), new Item(13919), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_VESTA_LEGS_DEG(new Item(13919), null, new DegradeTime(TimeUnit.MINUTES, 15)),

		/**
		 * @CorruptZuriels
		 */

		CORRUPT_ZURIELS_HOOD(new Item(13938), new Item(13940), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_ZURIELS_HOOD_DEG(new Item(13940), null, new DegradeTime(TimeUnit.MINUTES, 15)),
		CORRUPT_ZURIELS_TOP(new Item(13932), new Item(13934), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_ZURIELS_TOP_DEG(new Item(13934), null, new DegradeTime(TimeUnit.MINUTES, 15)),
		CORRUPT_ZURIELS_LEGS(new Item(13935), new Item(13937), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_ZURIELS_LEGS_DEG(new Item(13937), null, new DegradeTime(TimeUnit.MINUTES, 15)),

		CORRUPT_ZURIELS_STAFF(new Item(13941), new Item(13943), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_ZURIELS_STAFF_DEG(new Item(13943), null, new DegradeTime(TimeUnit.MINUTES, 15)),

		/**
		 * @CorruptMorrigans
		 */
		CORRUPT_MORRIGANS_COIF(new Item(13950), new Item(13952), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_MORRIGANS_COIF_DEG(new Item(13952), null, new DegradeTime(TimeUnit.MINUTES, 15)),
		CORRUPT_MORRIGANS_TOP(new Item(13944), new Item(13946), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_MORRIGANS_TOP_DEG(new Item(13946), null, new DegradeTime(TimeUnit.MINUTES, 15)),
		CORRUPT_MORRIGANS_CHAPS(new Item(13947), new Item(13949), new DegradeTime(TimeUnit.SECONDS, 30)),
		CORRUPT_MORRIGANS_CHAPS_DEG(new Item(13949), null, new DegradeTime(TimeUnit.MINUTES, 15)),

		/**
		 * @Statius
		 */

		STATIUS_HELM(new Item(13896), new Item(13898), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		STATIUS_HELM_DEG(new Item(13898), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),
		STATIUS_PLATEBODY(new Item(13884), new Item(14886), DegradeType.IN_COMBAT,
				new DegradeTime(TimeUnit.SECONDS, 30)),
		STATIUS_PLATEBODY_DEG(new Item(14886), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),
		STATIUS_PLATELEGS(new Item(13890), new Item(13892), DegradeType.IN_COMBAT,
				new DegradeTime(TimeUnit.SECONDS, 30)),
		STATIUS_PLATELEGS_DEG(new Item(13892), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),

		STATIUS_WARHAMMER(new Item(13902), new Item(13904), DegradeType.IN_COMBAT,
				new DegradeTime(TimeUnit.SECONDS, 30)),
		STATIUS_WARHAMMER_DEG(new Item(13904), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),

		/**
		 * @Vesta
		 */

		VESTA_CHAINBODY(new Item(13887), new Item(13889), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		VESTA_CHAINBODY_DEG(new Item(13889), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),
		VESTA_PLATESKIRT(new Item(13893), new Item(13895), DegradeType.IN_COMBAT,
				new DegradeTime(TimeUnit.SECONDS, 30)),
		VESTA_PLATESKIRT_DEG(new Item(13895), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),

		VESTA_LONGSWORD(new Item(13899), new Item(13901), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		VESTA_LONGSWORD_DEG(new Item(13901), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),

		VESTA_SPEAR(new Item(13905), new Item(13907), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		VESTA_SPEAR_DEG(new Item(13907), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),

		/**
		 * @Zuriels
		 */

		ZURIELS_HOOD(new Item(13864), new Item(13866), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		ZURIELS_HOOD_DEG(new Item(13866), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),
		ZURIELS_TOP(new Item(13858), new Item(13860), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		ZURIELS_TOP_DEG(new Item(13860), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),
		ZURIELS_LEGS(new Item(13861), new Item(13863), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		ZURIELS_LEGS_DEG(new Item(13863), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),

		ZURIELS_STAFF(new Item(13867), new Item(13869), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		ZURIELS_STAFF_DEG(new Item(13869), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),

		/**
		 * @Morrigans
		 */

		MORRIGANS_COIF(new Item(13876), new Item(13878), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		MORRIGANS_COIF_DEG(new Item(13878), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),
		MORRIGANS_BODY(new Item(13870), new Item(13872), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		MORRIGANS_BODY_DEG(new Item(13872), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),
		MORRIGANS_CHAPS(new Item(13873), new Item(13875), DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.SECONDS, 30)),
		MORRIGANS_CHAPS_DEG(new Item(13875), null, DegradeType.IN_COMBAT, new DegradeTime(TimeUnit.MINUTES, 30)),

		/**
		 * @Ahrims
		 */

		AHRIMS_HOOD(new Item(4708), new Item(4856), DegradeType.IN_COMBAT, new Item(4860),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		AHRIMS_HOOD_100(new Item(4856), new Item(4857), DegradeType.IN_COMBAT, new Item(4860),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_HOOD_75(new Item(4857), new Item(4858), DegradeType.IN_COMBAT, new Item(4860),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_HOOD_50(new Item(4858), new Item(4859), DegradeType.IN_COMBAT, new Item(4860),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_HOOD_25(new Item(4859), null, DegradeType.IN_COMBAT, new Item(4860),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		AHRIMS_TOP(new Item(4712), new Item(4868), DegradeType.IN_COMBAT, new Item(4872),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		AHRIMS_TOP_100(new Item(4868), new Item(4869), DegradeType.IN_COMBAT, new Item(4872),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_TOP_75(new Item(4869), new Item(4870), DegradeType.IN_COMBAT, new Item(4872),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_TOP_50(new Item(4870), new Item(4871), DegradeType.IN_COMBAT, new Item(4872),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_TOP_25(new Item(4871), null, DegradeType.IN_COMBAT, new Item(4872),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		AHRIMS_BOTTOM(new Item(4714), new Item(4874), DegradeType.IN_COMBAT, new Item(4878),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		AHRIMS_BOTTOM_100(new Item(4874), new Item(4875), DegradeType.IN_COMBAT, new Item(4878),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_BOTTOM_75(new Item(4875), new Item(4876), DegradeType.IN_COMBAT, new Item(4878),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_BOTTOM_50(new Item(4876), new Item(4877), DegradeType.IN_COMBAT, new Item(4878),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_BOTTOM_25(new Item(4877), null, DegradeType.IN_COMBAT, new Item(4878),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		AHRIMS_STAFF(new Item(4710), new Item(4862), DegradeType.IN_COMBAT, new Item(4866),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		AHRIMS_STAFF_100(new Item(4862), new Item(4863), DegradeType.IN_COMBAT, new Item(4866),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_STAFF_75(new Item(4863), new Item(4864), DegradeType.IN_COMBAT, new Item(4866),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_STAFF_50(new Item(4864), new Item(4865), DegradeType.IN_COMBAT, new Item(4866),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		AHRIMS_STAFF_25(new Item(4865), null, DegradeType.IN_COMBAT, new Item(4866),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		/**
		 * @Dharoks
		 */

		DHAROKS_HELM(new Item(4716), new Item(4880), DegradeType.IN_COMBAT, new Item(4884),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		DHAROKS_HELM_100(new Item(4880), new Item(4881), DegradeType.IN_COMBAT, new Item(4884),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_HELM_75(new Item(4881), new Item(4882), DegradeType.IN_COMBAT, new Item(4884),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_HELM_50(new Item(4882), new Item(4883), DegradeType.IN_COMBAT, new Item(4884),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_HELM_25(new Item(4883), null, DegradeType.IN_COMBAT, new Item(4884),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		DHAROKS_AXE(new Item(4718), new Item(4886), DegradeType.IN_COMBAT, new Item(4890),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		DHAROKS_AXE_100(new Item(4886), new Item(4887), DegradeType.IN_COMBAT, new Item(4890),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_AXE_75(new Item(4887), new Item(4888), DegradeType.IN_COMBAT, new Item(4890),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_AXE_50(new Item(4888), new Item(4889), DegradeType.IN_COMBAT, new Item(4890),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_AXE_25(new Item(4889), null, DegradeType.IN_COMBAT, new Item(4890),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		DHAROKS_PLATEBODY(new Item(4720), new Item(4892), DegradeType.IN_COMBAT, new Item(4896),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		DHAROKS_PLATEBODY_100(new Item(4892), new Item(4893), DegradeType.IN_COMBAT, new Item(4896),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_PLATEBODY_75(new Item(4893), new Item(4894), DegradeType.IN_COMBAT, new Item(4896),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_PLATEBODY_50(new Item(4894), new Item(4895), DegradeType.IN_COMBAT, new Item(4896),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_PLATEBODY_25(new Item(4895), null, DegradeType.IN_COMBAT, new Item(4896),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		DHAROKS_PLATELEGS(new Item(4722), new Item(4898), DegradeType.IN_COMBAT, new Item(4902),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		DHAROKS_PLATELEGS_100(new Item(4898), new Item(4899), DegradeType.IN_COMBAT, new Item(4902),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_PLATELEGS_75(new Item(4899), new Item(4900), DegradeType.IN_COMBAT, new Item(4902),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_PLATELEGS_50(new Item(4900), new Item(4901), DegradeType.IN_COMBAT, new Item(4902),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		DHAROKS_PLATELEGS_25(new Item(4901), null, DegradeType.IN_COMBAT, new Item(4902),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		/**
		 * @Guthans
		 */

		GUTHANS_HELM(new Item(4724), new Item(4904), DegradeType.IN_COMBAT, new Item(4908),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		GUTHANS_HELM_100(new Item(4904), new Item(4905), DegradeType.IN_COMBAT, new Item(4908),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_HELM_75(new Item(4905), new Item(4906), DegradeType.IN_COMBAT, new Item(4908),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_HELM_50(new Item(4906), new Item(4907), DegradeType.IN_COMBAT, new Item(4908),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_HELM_25(new Item(4907), null, DegradeType.IN_COMBAT, new Item(4908),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		GUTHANS_SPEAR(new Item(4726), new Item(4910), DegradeType.IN_COMBAT, new Item(4914),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		GUTHANS_SPEAR_100(new Item(4910), new Item(4911), DegradeType.IN_COMBAT, new Item(4914),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_SPEAR_75(new Item(4911), new Item(4912), DegradeType.IN_COMBAT, new Item(4914),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_SPEAR_50(new Item(4912), new Item(4913), DegradeType.IN_COMBAT, new Item(4914),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_SPEAR_25(new Item(4913), null, DegradeType.IN_COMBAT, new Item(4914),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		GUTHANS_PLATEBODY(new Item(4728), new Item(4916), DegradeType.IN_COMBAT, new Item(4920),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		GUTHANS_PLATEBODY_100(new Item(4916), new Item(4917), DegradeType.IN_COMBAT, new Item(4920),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_PLATEBODY_75(new Item(4917), new Item(4918), DegradeType.IN_COMBAT, new Item(4920),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_PLATEBODY_50(new Item(4918), new Item(4919), DegradeType.IN_COMBAT, new Item(4920),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_PLATEBODY_25(new Item(4919), null, DegradeType.IN_COMBAT, new Item(4920),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		GUTHANS_PLATELEGS(new Item(4730), new Item(4922), DegradeType.IN_COMBAT, new Item(4926),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		GUTHANS_PLATELEGS_100(new Item(4922), new Item(4923), DegradeType.IN_COMBAT, new Item(4926),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_PLATELEGS_75(new Item(4923), new Item(4924), DegradeType.IN_COMBAT, new Item(4926),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_PLATELEGS_50(new Item(4924), new Item(4925), DegradeType.IN_COMBAT, new Item(4926),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		GUTHANS_PLATELEGS_25(new Item(4925), null, DegradeType.IN_COMBAT, new Item(4926),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		/**
		 * @Karils
		 */

		KARILS_COIF(new Item(4732), new Item(4928), DegradeType.IN_COMBAT, new Item(4932),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		KARILS_COIF_100(new Item(4928), new Item(4929), DegradeType.IN_COMBAT, new Item(4932),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_COIF_75(new Item(4929), new Item(4930), DegradeType.IN_COMBAT, new Item(4932),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_COIF_50(new Item(4930), new Item(4931), DegradeType.IN_COMBAT, new Item(4932),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_COIF_25(new Item(4931), null, DegradeType.IN_COMBAT, new Item(4932),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		KARILS_CROSSBOW(new Item(4734), new Item(4934), DegradeType.IN_COMBAT, new Item(4938),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		KARILS_CROSSBOW_100(new Item(4934), new Item(4935), DegradeType.IN_COMBAT, new Item(4938),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_CROSSBOW_75(new Item(4935), new Item(4936), DegradeType.IN_COMBAT, new Item(4938),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_CROSSBOW_50(new Item(4936), new Item(4937), DegradeType.IN_COMBAT, new Item(4938),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_CROSSBOW_25(new Item(4937), null, DegradeType.IN_COMBAT, new Item(4938),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		KARILS_TOP(new Item(4736), new Item(4940), DegradeType.IN_COMBAT, new Item(4944),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		KARILS_TOP_100(new Item(4940), new Item(4941), DegradeType.IN_COMBAT, new Item(4944),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_TOP_75(new Item(4941), new Item(4942), DegradeType.IN_COMBAT, new Item(4944),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_TOP_50(new Item(4942), new Item(4943), DegradeType.IN_COMBAT, new Item(4944),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_TOP_25(new Item(4943), null, DegradeType.IN_COMBAT, new Item(4944),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		KARILS_SKIRT(new Item(4738), new Item(4946), DegradeType.IN_COMBAT, new Item(4950),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		KARILS_SKIRT_100(new Item(4946), new Item(4947), DegradeType.IN_COMBAT, new Item(4950),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_SKIRT_75(new Item(4947), new Item(4948), DegradeType.IN_COMBAT, new Item(4950),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_SKIRT_50(new Item(4948), new Item(4949), DegradeType.IN_COMBAT, new Item(4950),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		KARILS_SKIRT_25(new Item(4949), null, DegradeType.IN_COMBAT, new Item(4950),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		/**
		 * @Torags
		 */

		TORAGS_HELM(new Item(4745), new Item(4952), DegradeType.IN_COMBAT, new Item(4956),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		TORAGS_HELM_100(new Item(4952), new Item(4953), DegradeType.IN_COMBAT, new Item(4956),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_HELM_75(new Item(4953), new Item(4954), DegradeType.IN_COMBAT, new Item(4956),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_HELM_50(new Item(4954), new Item(4955), DegradeType.IN_COMBAT, new Item(4956),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_HELM_25(new Item(4955), null, DegradeType.IN_COMBAT, new Item(4956),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		TORAGS_HAMMERS(new Item(4747), new Item(4958), DegradeType.IN_COMBAT, new Item(4962),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		TORAGS_HAMMERS_100(new Item(4958), new Item(4959), DegradeType.IN_COMBAT, new Item(4962),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_HAMMERS_75(new Item(4959), new Item(4960), DegradeType.IN_COMBAT, new Item(4962),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_HAMMERS_50(new Item(4960), new Item(4961), DegradeType.IN_COMBAT, new Item(4962),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_HAMMERS_25(new Item(4961), null, DegradeType.IN_COMBAT, new Item(4962),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		TORAGS_PLATEBODY(new Item(4749), new Item(4964), DegradeType.IN_COMBAT, new Item(4968),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		TORAGS_PLATEBODY_100(new Item(4964), new Item(4965), DegradeType.IN_COMBAT, new Item(4968),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_PLATEBODY_75(new Item(4965), new Item(4966), DegradeType.IN_COMBAT, new Item(4968),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_PLATEBODY_50(new Item(4966), new Item(4967), DegradeType.IN_COMBAT, new Item(4968),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_PLATEBODY_25(new Item(4967), null, DegradeType.IN_COMBAT, new Item(4968),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		TORAGS_PLATELEGS(new Item(4751), new Item(4970), DegradeType.IN_COMBAT, new Item(4974),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		TORAGS_PLATELEGS_100(new Item(4970), new Item(4971), DegradeType.IN_COMBAT, new Item(4974),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_PLATELEGS_75(new Item(4971), new Item(4972), DegradeType.IN_COMBAT, new Item(4974),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_PLATELEGS_50(new Item(4972), new Item(4973), DegradeType.IN_COMBAT, new Item(4974),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		TORAGS_PLATELEGS_25(new Item(4973), null, DegradeType.IN_COMBAT, new Item(4974),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		/**
		 * @Veracs
		 */

		VERACS_HELM(new Item(4753), new Item(4976), DegradeType.IN_COMBAT, new Item(4980),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		VERACS_HELM_100(new Item(4976), new Item(4977), DegradeType.IN_COMBAT, new Item(4980),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_HELM_75(new Item(4977), new Item(4978), DegradeType.IN_COMBAT, new Item(4980),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_HELM_50(new Item(4978), new Item(4979), DegradeType.IN_COMBAT, new Item(4980),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_HELM_25(new Item(4979), null, DegradeType.IN_COMBAT, new Item(4980),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		VERACS_FLAIL(new Item(4755), new Item(4982), DegradeType.IN_COMBAT, new Item(4986),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		VERACS_FLAIL_100(new Item(4982), new Item(4983), DegradeType.IN_COMBAT, new Item(4986),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_FLAIL_75(new Item(4983), new Item(4984), DegradeType.IN_COMBAT, new Item(4986),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_FLAIL_50(new Item(4984), new Item(4985), DegradeType.IN_COMBAT, new Item(4986),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_FLAIL_25(new Item(4985), null, DegradeType.IN_COMBAT, new Item(4986),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		VERACS_BRASSARD(new Item(4757), new Item(4988), DegradeType.IN_COMBAT, new Item(4992),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		VERACS_BRASSARD_100(new Item(4988), new Item(4989), DegradeType.IN_COMBAT, new Item(4992),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_BRASSARD_75(new Item(4989), new Item(4990), DegradeType.IN_COMBAT, new Item(4992),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_BRASSARD_50(new Item(4990), new Item(4991), DegradeType.IN_COMBAT, new Item(4992),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_BRASSARD_25(new Item(4991), null, DegradeType.IN_COMBAT, new Item(4992),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		VERACS_SKIRT(new Item(4759), new Item(4994), DegradeType.IN_COMBAT, new Item(4998),
				new DegradeTime(TimeUnit.SECONDS, 1)),
		VERACS_SKIRT_100(new Item(4994), new Item(4995), DegradeType.IN_COMBAT, new Item(4998),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_SKIRT_75(new Item(4995), new Item(4996), DegradeType.IN_COMBAT, new Item(4998),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_SKIRT_50(new Item(4996), new Item(4997), DegradeType.IN_COMBAT, new Item(4998),
				new DegradeTime(TimeUnit.MINUTES, 225)),
		VERACS_SKIRT_25(new Item(4997), null, DegradeType.IN_COMBAT, new Item(4998),
				new DegradeTime(TimeUnit.MINUTES, 225)),

		/**
		 * @CrystalBow
		 */
		NEW_CRYSTAL_BOW(new Item(4212), new Item(4214), DegradeType.AT_OUTGOING_HIT, 1),
		FULL_CRYSTAL_BOW(new Item(4214), new Item(4215), DegradeType.AT_OUTGOING_HIT, 250),
		CRYSTAL_BOW_9(new Item(4215), new Item(4216), DegradeType.AT_OUTGOING_HIT, 250),
		CRYSTAL_BOW_8(new Item(4216), new Item(4217), DegradeType.AT_OUTGOING_HIT, 250),
		CRYSTAL_BOW_7(new Item(4217), new Item(4218), DegradeType.AT_OUTGOING_HIT, 250),
		CRYSTAL_BOW_6(new Item(4218), new Item(4219), DegradeType.AT_OUTGOING_HIT, 250),
		CRYSTAL_BOW_5(new Item(4219), new Item(4220), DegradeType.AT_OUTGOING_HIT, 250),
		CRYSTAL_BOW_4(new Item(4220), new Item(4221), DegradeType.AT_OUTGOING_HIT, 250),
		CRYSTAL_BOW_3(new Item(4221), new Item(4222), DegradeType.AT_OUTGOING_HIT, 250),
		CRYSTAL_BOW_2(new Item(4222), new Item(4223), DegradeType.AT_OUTGOING_HIT, 250),
		CRYSTAL_BOW_1(new Item(4223), new Item(4207), DegradeType.AT_OUTGOING_HIT, 250),

		/**
		 * @CrystalShield
		 */
		NEW_CRYSTAL_SHIELD(new Item(4224), new Item(4225), DegradeType.AT_INCOMMING_HIT, 1),
		FULL_CRYSTAL_SHIELD(new Item(4225), new Item(4226), DegradeType.AT_INCOMMING_HIT, 250),
		CRYSTAL_SHIELD_9(new Item(4226), new Item(4227), DegradeType.AT_INCOMMING_HIT, 250),
		CRYSTAL_SHIELD_8(new Item(4227), new Item(4228), DegradeType.AT_INCOMMING_HIT, 250),
		CRYSTAL_SHIELD_7(new Item(4228), new Item(4229), DegradeType.AT_INCOMMING_HIT, 250),
		CRYSTAL_SHIELD_6(new Item(4229), new Item(4230), DegradeType.AT_INCOMMING_HIT, 250),
		CRYSTAL_SHIELD_5(new Item(4230), new Item(4231), DegradeType.AT_INCOMMING_HIT, 250),
		CRYSTAL_SHIELD_4(new Item(4231), new Item(4232), DegradeType.AT_INCOMMING_HIT, 250),
		CRYSTAL_SHIELD_3(new Item(4232), new Item(4233), DegradeType.AT_INCOMMING_HIT, 250),
		CRYSTAL_SHIELD_2(new Item(4233), new Item(4234), DegradeType.AT_INCOMMING_HIT, 250),
		CRYSTAL_SHIELD_1(new Item(4234), new Item(4207), DegradeType.AT_INCOMMING_HIT, 250),

		/**
		 * @Chaotics
		 */

		CHAOTIC_RAPIER(new Item(18349), new Item(18350), DegradeType.AT_OUTGOING_HIT, 30000),

		CHAOTIC_LONGSWORD(new Item(18351), new Item(18352), DegradeType.AT_OUTGOING_HIT, 30000),

		CHAOTIC_MAUL(new Item(18353), new Item(18354), DegradeType.AT_OUTGOING_HIT, 30000),

		CHAOTIC_STAFF(new Item(18355), new Item(18356), DegradeType.AT_OUTGOING_HIT, 30000),

		CHAOTIC_CROSSBOW(new Item(18357), new Item(18358), DegradeType.AT_OUTGOING_HIT, 30000),

		CHAOTIC_KITESHIELD(new Item(18359), new Item(18360), DegradeType.AT_INCOMMING_HIT, 30000),

		EAGLE_EYE_SHIELD(new Item(18361), new Item(18362), DegradeType.AT_INCOMMING_HIT, 30000),

		FARSEER_SHIELD(new Item(18363), new Item(18364), DegradeType.AT_INCOMMING_HIT, 30000),
		
		
		/**
		 * Ringofrecoil
		 */
		
		RING_OF_RECOIL(new Item(2550), null, DegradeType.HITS, 400)

		;

		private Item currentItem, degradedItem, brokenItem;
		private DegradeType type;
		private DegradeTime time;
		private int hits;

		private ItemStore(Item currentItem, Item degradedItem, DegradeType type, DegradeTime time) {// non corrupt
			this(currentItem, degradedItem, type, null, time, -1);
		}

		private ItemStore(Item currentItem, Item degradedItem, DegradeTime time) {// corrupt
			this(currentItem, degradedItem, DegradeType.WEAR, null, time, -1);
		}

		private ItemStore(Item currentItem, Item degradedItem, DegradeType type, Item brokenItem, DegradeTime time) {// barrows
			this(currentItem, degradedItem, type, brokenItem, time, -1);
		}

		private ItemStore(Item currentItem, Item degradedItem, DegradeType type, int hits) {// crystal
			this(currentItem, degradedItem, type, null, null, hits);
		}

		private ItemStore(Item currentItem, Item degradedItem, DegradeType type, Item brokenItem, DegradeTime time,
				int hits) {
			this.currentItem = currentItem;
			this.degradedItem = degradedItem;
			this.type = type;
			this.brokenItem = brokenItem;
			this.time = time;
			this.hits = hits;
		}

		public Item getCurrentItem() {
			return currentItem;
		}

		public Item getDegradedItem() {
			return degradedItem;
		}

		public Item getBrokenItem() {
			return brokenItem;
		}

		public DegradeType getType() {
			return type;
		}

		public DegradeTime getTime() {
			return time;
		}

		public int getHits() {
			return hits;
		}
	}
}

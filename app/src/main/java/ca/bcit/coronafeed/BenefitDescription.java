package ca.bcit.coronafeed;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class BenefitDescription {
    private String BenefitDescriptionId;
    private String title;
    private String link;
    private String province;

    public BenefitDescription() { }


    public BenefitDescription(String benefitDescriptionId, String title, String link, String province) {
        BenefitDescriptionId = benefitDescriptionId;
        this.title = title;
        this.link = link;
        this.province = province;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}

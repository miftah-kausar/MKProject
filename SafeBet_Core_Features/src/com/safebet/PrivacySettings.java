package com.safebet;

import java.util.Arrays;
import java.util.List;

public class PrivacySettings {
    private boolean shareWithInsurance;
    private boolean shareAnonymizedData;
    private List<String> dataSharedFields;

    public PrivacySettings() {
        this.shareWithInsurance = false;
        this.shareAnonymizedData = true;
        this.dataSharedFields = Arrays.asList(
            "averageSpeed", 
            "harshAccelerations", 
            "harshBrakings"
        );
    }

    // Getters and setters for privacy settings
    public void toggleInsuranceSharing(boolean share) {
        this.shareWithInsurance = share;
    }
}

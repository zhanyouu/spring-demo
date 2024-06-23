package com.zhanyou.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class UserConstants {
    public static final List<String> AuthorizedPersonAttachmentTypeList = new ArrayList<String>(Arrays.asList("IdentityIdFront", "IdentityIdBack", "AddressProof", "PowerOfAttorney"));
    public static final List<String> LegalRepresentativeAttachmentTypeList = new ArrayList<String>(Arrays.asList("IdentityIdFront", "IdentityIdBack", "AddressProof"));
    public static final List<String> OrdinaryLicenseAttachmentTypeList = new ArrayList<String>(Arrays.asList("OrganizationCodeCertificate", "TaxErolCertificate", "BusinessLicense", "AccountApplyForm", "MoreAccessories", "OriginalAccountApplyForm"));
    public static final List<String> ThreeInOneLicenseAttachmentTypeList = new ArrayList<String>(Arrays.asList("ThreeInOneLicense", "BusinessLicense", "MoreAccessories", "AccountApplyForm", "OriginalAccountApplyForm"));
    public static final List<String> HongKongLicenseAttachmentTypeList = new ArrayList<String>(Arrays.asList("BoardResolution", "CertificateOfIncorporation", "BusinessRegistrationCertificate", "FormNC", "AnnualReturn", "MoreAccessories", "AccountApplyForm", "OriginalAccountApplyForm"));
    public static final List<String> OthersLicenseAttachmentTypeList = new ArrayList<String>(Arrays.asList("CertificateRelatedToFirm", "MoreAccessories", "AccountApplyForm", "OriginalAccountApplyForm"));

//    public static final List<String> AuthorizedPersonAttachmentTypeUpdateList = new ArrayList<String>(Arrays.asList("IdentityIdFrontUpdate", "IdentityIdBackUpdate", "AddressProofUpdate", "PowerOfAttorneyUpdate"));
//    public static final List<String> LegalRepresentativeAttachmentTypeUpdateList = new ArrayList<String>(Arrays.asList("IdentityIdFrontUpdate", "IdentityIdBackUpdate", "AddressProofUpdate"));
//    public static final List<String> OrdinaryLicenseAttachmentTypeUpdateList = new ArrayList<String>(Arrays.asList("OrganizationCodeCertificateUpdate", "TaxErolCertificateUpdate", "BusinessLicenseUpdate"));

    //    public final static int MaxPasswordRetry = 5;
    public static final String TENANT_HEADER = "X-TenantID";
    public static final String LINK_HEADER = "X-LINK";
    public static final String APP_NAME = "spring.application.name";

    public static final String XT_MID = "XTransfer";
    public static final int XTRANSFER_INIT_VERSION = 1;

    //Session Constants
    public static final String USERNAME_COOKIE_NAME = "aun";
    //    public static final String ERROR = "EORROR";
    // Page Constants
//    public static final String USER_TITLE = "UserTitle";
    public static final String REGION_CN = "CN";

    public static final String FIRM_ID = "firmId";
    public static final String USER_ID = "userId";
//    public static final String DATA_ACCESS_LEVEL = "dataAccessLevel";

    public static final String COMMO = ":";
}

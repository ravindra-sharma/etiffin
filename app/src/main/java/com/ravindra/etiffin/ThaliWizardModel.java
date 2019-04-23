package com.ravindra.etiffin;

import android.content.Context;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Cod_PlYr on 1/23/2017.
 */
public class ThaliWizardModel extends AbstractWizardModel {
    Context ctx;
    String[] thali_list;

    public ThaliWizardModel(Context context) {
        super(context);
        ctx = context;

    }

    @Override
    protected PageList onNewRootPageList() {
        String[] thali_list = {"30", "40", "50"};
        String[] thali30 = {"chapati", "vegetables", "Rice", "liquidfood"};
        String[] thali40 = {"chapati", "vegetables", "Rice", "liquidfood", "salad", "pickle"};
        String[] thali50 = {"chapati", "vegetables", "Rice", "liquidfood", "salad", "pickle", "sweet"};
        String[] chapatithali30 = {"gehu", "bajra", "mix grain", "makka"};
        String[] vegetablesthali30 = {"aloo gobhi", "bhindi", "aloo matar", "gajar"};
        String[] Ricethali30 = {"plain", "brown", "biryani"};
        String[] liquidfoodthali30 = {"chana dal", "moong dal", "mix dal", "pyaj curry"};
        String[] chapatithali40 = {"gehu", "bajra", "mix grain", "makka"};
        String[] vegetablesthali40 = {"aloo gobhi", "bhindi", "aloo matar", "gajar"};
        String[] Ricethali40 = {"plain", "brown", "biryani"};
        String[] liquidfoodthali40 = {"chana dal", "moong dal", "mix dal", "pyaj curry"};
        String[] saladthali40 = {"pyaj", "green", "cucumber"};
        String[] picklethali40 = {"cerry", "nimbu", "gajar"};
        String[] chapatithali50 = {"gehu", "bajra", "mix grain", "makka"};
        String[] vegetablesthali50 = {"aloo gobhi", "bhindi", "aloo matar", "gajar"};
        String[] Ricethali50 = {"plain", "brown", "biryani"};
        String[] liquidfoodthali50 = {"chana dal", "moong dal", "mix dal", "pyaj curry"};
        String[] saladthali50 = {"pyaj", "green", "cucumber"};
        String[] picklethali50 = {"cerry", "nimbu", "gajar"};
        String[] sweetthali50 = {"rasgulla", "gulabjamun", "ras malai"};
        HashMap<String, String[]> map = new HashMap<String, String[]>();
        map.put("thali_list", thali_list);
        map.put("thali30", thali30);
        map.put("thali40", thali40);
        map.put("thali50", thali50);
        map.put("chapatithali30", chapatithali30);
        map.put("chapatithali40", chapatithali40);
        map.put("chapatithali50", chapatithali50);
        map.put("vegetablesthali30", vegetablesthali30);
        map.put("vegetablesthali40", vegetablesthali40);
        map.put("vegetablesthali50", vegetablesthali50);
        map.put("Ricethali30", Ricethali30);
        map.put("Ricethali40", Ricethali40);
        map.put("Ricethali50", Ricethali50);
        map.put("liquidfoodthali30", liquidfoodthali30);
        map.put("liquidfoodthali40", liquidfoodthali40);
        map.put("liquidfoodthali50", liquidfoodthali50);
        map.put("saladthali40", saladthali40);
        map.put("saladthali50", saladthali50);
        map.put("picklethali40", picklethali40);
        map.put("picklethali50", picklethali50);
        map.put("sweetthali50", sweetthali50);

        BranchPage b = new BranchPage(this, "Select Your Price:-");
        String[] thalilist = map.get("thali_list");
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < thalilist.length; i++) {
            arrayList.clear();
            String[] thali = map.get("thali" + thalilist[i]);
            for (int j = 0; j < thali.length; j++) {
                arrayList.add(new SingleFixedChoicePage(this, thali[j]).setChoices(map.get(thali[j] + "thali" + thalilist[i])));
            }
            SingleFixedChoicePage[] array = new SingleFixedChoicePage[arrayList.size()];
            array = (SingleFixedChoicePage[]) arrayList.toArray(array);
            b.addBranch(thalilist[i], array);
        }
        return new PageList(b);
    }

}

package com.ecms.ndmecms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ecms.ndmecms.Activity.ReportActivity;
import com.ecms.ndmecms.Adapters.ExpandableListAdapter;
import com.ecms.ndmecms.Fragments.MessagesFragment;
import com.ecms.ndmecms.Fragments.SearchFragment;
import com.ecms.ndmecms.Models.MenuModel;
import com.ecms.ndmecms.ui.MyService;
import com.ecms.ndmecms.ui.ReadyToApprove;
import com.ecms.ndmecms.ui.UserMessages;
import com.ecms.ndmecms.ui.ViewEmployeeActivity;
import com.ecms.ndmecms.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;

import com.google.android.play.core.tasks.OnFailureListener;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AppUpdateManager appUpdateManager;
    private static final int RC_APP_UPDATE=100;
    private AppBarConfiguration mAppBarConfiguration;
    private static int SPLASH_TIME_OUT=3000;
    public static Boolean stopService= false;

    Toolbar toolbar;

    Boolean checkbox=false;
    TextView navigationWelcome;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();

    private DrawerLayout drawer;
    private ChipNavigationBar bottomNavigationView;


    private HomeFragment homeFragment = HomeFragment.newInstance();
    private MessagesFragment messagesFragment = MessagesFragment.newInstance();
    private SearchFragment searchFragment = SearchFragment.newInstance();
View viewHeader;


    @Override
    protected void onStop() {
       if(appUpdateManager!=null) appUpdateManager.unregisterListener(installStateUpdatedListener);
        super.onStop();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private InstallStateUpdatedListener installStateUpdatedListener=new InstallStateUpdatedListener() {
        @Override
        public void onStateUpdate(InstallState state) {
        if(state.installStatus() == InstallStatus.DOWNLOADED){
            showCompletedUpdate();
        }
        }
    };

    private void showCompletedUpdate() {
        Snackbar snackbar= Snackbar.make(findViewById(android.R.id.content),"New app is ready",Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appUpdateManager.completeUpdate();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(requestCode ==RC_APP_UPDATE && resultCode !=RESULT_OK){
          // Toast.makeText(this, "not done", Toast.LENGTH_SHORT).show();
       }

        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //from here to
        appUpdateManager=AppUpdateManagerFactory.create(this);

        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {
           if(result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                   result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){
               try {
                   appUpdateManager.startUpdateFlowForResult(result,AppUpdateType.FLEXIBLE,MainActivity.this,RC_APP_UPDATE);
               } catch (IntentSender.SendIntentException e) {
                   e.printStackTrace();
               }
           }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(Exception e) {
               // Toast.makeText(MainActivity.this, "no need of update", Toast.LENGTH_SHORT).show();
            }
        });

        appUpdateManager.registerListener(installStateUpdatedListener);
        //here

        /*Intent startIntent = new Intent(getApplicationContext(), MyService.class);
        startIntent.setAction("startService");
        startIntent.putExtra("service", "yes");
        getApplicationContext().startService(startIntent);*/



        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        viewHeader = navigationView.getHeaderView(0);
        ImageView imageViewNavheaderPRofile = (ImageView) viewHeader.findViewById(R.id.nav_header_profile_imageview);
        ImageView imageViewNavheaderLogout = (ImageView) viewHeader.findViewById(R.id.nav_header_logout_iv);
        navigationWelcome = viewHeader.findViewById(R.id.navigation_welcome);

        navigationWelcome.setText("Welcome, " + PreferenceUtils.getUserName(this));
        navigationWelcome.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        imageViewNavheaderPRofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        imageViewNavheaderLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.savePassword(null, MainActivity.this);
                PreferenceUtils.saveEmail(null, MainActivity.this);
                PreferenceUtils.saveUid(null, MainActivity.this);
               // MyService.count = 0;

                //trying to stop the service
              /*  stopService = true;
                Intent stopIntent = new Intent(getApplicationContext(), MyService.class);
                stopIntent.putExtra("service", "yes");
                stopIntent.setAction("stopService");
                getApplicationContext().startService(stopIntent);*/
                final ProgressDialog progressDoalog;
                progressDoalog = new ProgressDialog(MainActivity.this);
                progressDoalog.setMessage("Logging Out....");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.show();

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i);
                finish();
            }
        });

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


//        if (navigationView != null) {
//            setupDrawerContent(navigationView);
//        }

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();


        ImageButton menuRight = findViewById(R.id.toolbar_leftRight_image);

        menuRight.setOnClickListener(v -> {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                drawer.openDrawer(GravityCompat.START);
            }
        });


        navigationView.setNavigationItemSelectedListener(this);
//        Animation aniFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
//        Animation aniFadeout = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setItemSelected(R.id.navigation_home,true);

        bottomNavigationView.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {

                Fragment selectedFragment = null;
                switch (i) {
                    case R.id.navigation_home:
                        selectedFragment = homeFragment;
//                    floatingActionButtonAttendace.setAnimation(aniFadein);
//                    floatingActionButtonAttendace.show();
//                    textViewTitle.setText("John Doe");
//                    textViewSubtitle.setVisibility(View.VISIBLE);
//                    textViewSubtitle.setText("Software Developer");


                        break;
//                    case R.id.navigation_search:
//                        selectedFragment = searchFragment;
//                    floatingActionButtonAttendace.setAnimation(aniFadeout);
//                    floatingActionButtonAttendace.hide();
//                    textViewTitle.setText("Reports");
//                    textViewSubtitle.setVisibility(View.GONE);
//                        break;
//                    case R.id.navigation_messages:
//
//                        selectedFragment = messagesFragment;
//                    floatingActionButtonAttendace.setAnimation(aniFadeout);
//                    floatingActionButtonAttendace.hide();
//                    textViewTitle.setText("Requests");
//                    textViewSubtitle.setVisibility(View.GONE);
//                        break;


                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (selectedFragment != null) {
                    transaction.replace(R.id.f_container, selectedFragment);
                    transaction.commit();
                }
            }
        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f_container, homeFragment);
        transaction.commit();

        }

      /*  bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = homeFragment;
//                    floatingActionButtonAttendace.setAnimation(aniFadein);
//                    floatingActionButtonAttendace.show();
//                    textViewTitle.setText("John Doe");
//                    textViewSubtitle.setVisibility(View.VISIBLE);
//                    textViewSubtitle.setText("Software Developer");


                    break;
                case R.id.navigation_search:
                    selectedFragment = searchFragment;
//                    floatingActionButtonAttendace.setAnimation(aniFadeout);
//                    floatingActionButtonAttendace.hide();
//                    textViewTitle.setText("Reports");
//                    textViewSubtitle.setVisibility(View.GONE);
                    break;
                case R.id.navigation_messages:

                    selectedFragment = messagesFragment;
//                    floatingActionButtonAttendace.setAnimation(aniFadeout);
//                    floatingActionButtonAttendace.hide();
//                    textViewTitle.setText("Requests");
//                    textViewSubtitle.setVisibility(View.GONE);
                    break;


            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (selectedFragment != null) {
                transaction.replace(R.id.f_container, selectedFragment);
                transaction.commit();
            }
            return true;
        });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.f_container, homeFragment);
        transaction.commit();
    }*/

    @Override
    public void onBackPressed() {
      /*  DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//        switch (item.getItemId()) {
//            case R.id.action_notifications:
//                Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getApplicationContext(),NotificationsActivity.class);
//                startActivity(intent);
//                return true;
//            case R.id.action_settings:
//                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


//    @Override
//    protected void onStop() {
//        stopService(new Intent(this, MyService.class));
//
//        super.onStop();
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

        switch (item.getItemId())
        {
            case R.id.offline_download:
                Intent intent = new Intent(MainActivity.this, ViewEmployeeActivity.class);
                startActivity(intent);
                return true;
            case R.id.report:
                Intent reportIntent = new Intent(MainActivity.this, ReportActivity.class);
                startActivity(reportIntent);
                return true;
            case R.id.action_privacy:
                Intent privacyIntent = new Intent(MainActivity.this, PrivacyPolicyActivity.class);
                startActivity(privacyIntent);
                return true;

//            case R.id.action_light_purple:
//                PreferenceUtils.savePassword(null, MainActivity.this);
//                PreferenceUtils.saveEmail(null, MainActivity.this);
//                PreferenceUtils.saveUid(null, MainActivity.this);
//                MyService.count = 0;
//
//                //trying to stop the service
//                stopService = true;
//                Intent stopIntent = new Intent(this, MyService.class);
//                stopIntent.putExtra("service", "yes");
//                stopIntent.setAction("stopService");
//                getApplicationContext().startService(stopIntent);
//
//                Intent i = new Intent(this, LoginActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                final ProgressDialog progressDoalog;
//                progressDoalog = new ProgressDialog(MainActivity.this);
//                progressDoalog.setMessage("Logging Out....");
//                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//                progressDoalog.show();
//                startActivity(i);
//                finish();

//            case R.id.action_notification:
               /* Intent intent1 = new Intent(MainActivity.this, Notification.class);
                startActivity(intent1);*/

            default:
                return super.onOptionsItemSelected(item);


        }




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawers();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()) {
            case R.id.nav_home:

               bottomNavigationView.setItemSelected(R.id.navigation_home,true);
                ft.replace(R.id.f_container, homeFragment);
                break;
//            case R.id.nav_gallery:
//                ft.replace(R.id.f_container, settingFragment);
//                break;
//            case R.id.nav_send:
//                ft.replace(R.id.f_container, settingFragment);
//                break;

        }

        ft.commit();
        return true;
    }
    private void prepareMenuData() {

        MenuModel menuModel = new MenuModel("Dashboard", true, false, "https://www.journaldev.com/9333/android-webview-example-tutorial"); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);

        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        if(!(LoginActivity.checkValue())) {
            menuModel = new MenuModel("Correspondences", true, true, ""); //Menu of Java Tutorials
            headerList.add(menuModel);
        }
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel childModel = new MenuModel("Incoming", false, false, "https://www.journaldev.com/7153/core-java-tutorial");
        childModelsList.add(childModel);

        childModel = new MenuModel("Outgoing", false, false, "https://www.journaldev.com/19187/java-fileinputstream");
        childModelsList.add(childModel);




        if (menuModel.hasChildren) {
            Log.d("API123","here");
            childList.put(menuModel, childModelsList);
        }
        if(!(LoginActivity.checkValue())) {
            childModelsList = new ArrayList<>();
            menuModel = new MenuModel("Actions", true, true, ""); //Menu of Python Tutorials
            headerList.add(menuModel);
        }
        childModel = new MenuModel("Tasks to Complete", false, false, "https://www.journaldev.com/19243/python-ast-abstract-syntax-tree");
        childModelsList.add(childModel);

        childModel = new MenuModel("Approve Correspondences", false, false, "https://www.journaldev.com/19226/python-fractions");
        childModelsList.add(childModel);

        childModel = new MenuModel("Attendance Meetings", false, false, "https://www.journaldev.com/19226/python-fractions");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        if(!(LoginActivity.checkValue())) {
            childModelsList = new ArrayList<>();
            menuModel = new MenuModel("File Plans", true, true, ""); //Menu of Python Tutorials
            headerList.add(menuModel);
        }
        childModel = new MenuModel("File Plan View", false, false, "https://www.journaldev.com/19243/python-ast-abstract-syntax-tree");
        childModelsList.add(childModel);

        if (menuModel.hasChildren) {
            childList.put(menuModel, childModelsList);
        }
        if(!(LoginActivity.checkValue())) {
            menuModel = new MenuModel("Resolution Register", true, false, "https://www.journaldev.com/9333/android-webview-example-tutorial"); //Menu of Android Tutorial. No sub menus
            headerList.add(menuModel);
        }
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
        childModelsList = new ArrayList<>();
        menuModel = new MenuModel("Meetings", true, true, ""); //Menu of Python Tutorials
        headerList.add(menuModel);
        if(!(LoginActivity.checkValue())) {
            childModel = new MenuModel("Add New", false, false, "https://www.journaldev.com/19243/python-ast-abstract-syntax-tree");
            childModelsList.add(childModel);

            childModel = new MenuModel("Manage", false, false, "https://www.journaldev.com/19226/python-fractions");
            childModelsList.add(childModel);

            childModel = new MenuModel("Ready to Approve", false, false, "https://www.journaldev.com/19226/python-fractions");
            childModelsList.add(childModel);

                childModel = new MenuModel("To Attend", false, false, "https://www.journaldev.com/19226/python-fractions");
                childModelsList.add(childModel);
                childList.put(menuModel, childModelsList);
            }
        else{

            childModel = new MenuModel("To Attend", false, false, "https://www.journaldev.com/19226/python-fractions");
            childModelsList.add(childModel);
            childList.put(menuModel, childModelsList);
        }
         menuModel = new MenuModel("Committee Management", true, false, "https://www.journaldev.com/9333/android-webview-example-tutorial"); //Menu of Android Tutorial. No sub menus
        headerList.add(menuModel);
        if (!menuModel.hasChildren) {
            childList.put(menuModel, null);
        }
//        childModelsList = new ArrayList<>();
//        menuModel = new MenuModel("Messages", true, true, ""); //Menu of Python Tutorials
//        headerList.add(menuModel);
//        childModel = new MenuModel("Admin Messages", false, false, "https://www.journaldev.com/19243/python-ast-abstract-syntax-tree");
//        childModelsList.add(childModel);
//
//        childModel = new MenuModel("User Messages", false, false, "https://www.journaldev.com/19226/python-fractions");
//        childModelsList.add(childModel);
//        if (menuModel.hasChildren) {
//            childList.put(menuModel, childModelsList);
//        }

    }

    private void populateExpandableList() {

        expandableListAdapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
//                        WebView webView = findViewById(R.id.webView);
//                        webView.loadUrl(headerList.get(groupPosition).url);
                       // onBackPressed();
                        Toast.makeText(MainActivity.this, "Dashboard", Toast.LENGTH_SHORT).show();
                    }
                }
                if (groupPosition==4){
                    startActivity(new Intent(MainActivity.this,ResolutionCorrespondenceActivity.class));

                }

                if(!(LoginActivity.checkValue())){
                if(groupPosition == 6){

                    Intent intent=new Intent(MainActivity.this,CommitteeManagement.class);
                    startActivity(intent);
                }}
                else{
                    if(groupPosition == 2){

                        Intent intent=new Intent(MainActivity.this,CommitteeManagement.class);
                        startActivity(intent);
                    }
                }


                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

if(!(LoginActivity.checkValue())) {
    if (groupPosition == 7) {
        if (childPosition == 0) {
            Intent intent = new Intent(MainActivity.this, AdminMessages.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, UserMessages.class);
            startActivity(intent);
        }
    }

    if (groupPosition == 5) {
        if (childPosition == 2) {
            Intent intent = new Intent(MainActivity.this, ReadyToApprove.class);
            startActivity(intent);
        }
    }

    if (groupPosition == 5) {
        if (childPosition == 0) {
            startActivity(new Intent(MainActivity.this, AddNewMeetingActivity.class));

        }
    }

    if (groupPosition == 5) {
        if (childPosition == 1) {
            startActivity(new Intent(MainActivity.this, ManageMeetingsActivity.class));

        }
    }
    if (groupPosition == 5){
        if (childPosition==3)
            startActivity(new Intent(MainActivity.this, ToAttendMeetingActivity.class));

    }

    if (groupPosition == 1) {
        if (childPosition == 0) {
            startActivity(new Intent(MainActivity.this, IncomingCorrespondenceActivity.class));

        }
    }

    if (groupPosition == 1) {
        if (childPosition == 1) {
            startActivity(new Intent(MainActivity.this, OutgoingCorrespondenceActivity.class));

        }
    }

    if (groupPosition == 2) {
        if (childPosition == 0) {
            startActivity(new Intent(MainActivity.this, AssignedCorrespondenceTaskActivity.class));

        }
    }
}else{

    if (groupPosition == 1) {
        if (childPosition == 0) {
            startActivity(new Intent(MainActivity.this, ToAttendMeetingActivity.class));
        }
    }
    if (groupPosition == 3) {
        if (childPosition == 0) {
            Intent intent = new Intent(MainActivity.this, AdminMessages.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, UserMessages.class);
            startActivity(intent);
        }
    }


}



                return false;
            }
        });
    }




}

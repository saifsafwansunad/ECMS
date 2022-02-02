package com.ecms.ndmecms.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.ecms.ndmecms.Activity.ReportActivity;
import com.ecms.ndmecms.Adapters.ExpandableListAdapter;
import com.ecms.ndmecms.AddNewMeetingActivity;
import com.ecms.ndmecms.AdminMessages;
import com.ecms.ndmecms.AssignedCorrespondenceTaskActivity;
import com.ecms.ndmecms.CommitteeManagement;
import com.ecms.ndmecms.IncomingCorrespondenceActivity;
import com.ecms.ndmecms.LoginActivity;
import com.ecms.ndmecms.MainActivity;
import com.ecms.ndmecms.ManageMeetingsActivity;
import com.ecms.ndmecms.Models.MenuModel;
import com.ecms.ndmecms.OutgoingCorrespondenceActivity;
import com.ecms.ndmecms.PreferenceUtils;
import com.ecms.ndmecms.PrivacyPolicyActivity;
import com.ecms.ndmecms.ProfileActivity;
import com.ecms.ndmecms.R;
import com.ecms.ndmecms.ResolutionCorrespondenceActivity;
import com.ecms.ndmecms.ToAttendMeetingActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
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

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserMessages extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {


    private AppUpdateManager appUpdateManager;
    private static final int RC_APP_UPDATE=100;
    TabLayout tabLayoutMessages;
    ViewPager viewPagerMessages;
    ImageView offline,dots,drawer_left;
    TextView profileName,logout_second,profile;
    Boolean checkbox=false;
    TextView navigationWelcome;
    ExpandableListAdapter expandableListAdapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>();
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>();
    View viewHeader;


    private DrawerLayout drawer;
    private ChipNavigationBar bottomNavigationView;

    public static UserMessages newInstance() {
        return new UserMessages();
    }

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
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
      //  getSupportActionBar().hide();

        setContentView(R.layout.mainactivity2);



        //from here to
        appUpdateManager= AppUpdateManagerFactory.create(this);

        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo result) {
                if(result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                        result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){
                    try {
                        appUpdateManager.startUpdateFlowForResult(result,AppUpdateType.FLEXIBLE,UserMessages.this,RC_APP_UPDATE);
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

     //   View view = inflater.inflate(R.layout.fragment_inbox, container, false);



        TextView title=findViewById(R.id.title);
        title.setText("NDM ECMS");
        tabLayoutMessages = (TabLayout) findViewById(R.id.messages_tablayout);
        viewPagerMessages = (ViewPager) findViewById(R.id.messages_viewPager);
        drawer_left=(ImageView) findViewById(R.id.imgBackArrow);
        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Meetings"));
        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Public"));
        tabLayoutMessages.addTab(tabLayoutMessages.newTab().setText("Private"));

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        viewHeader = navigationView.getHeaderView(0);
        ImageView imageViewNavheaderPRofile = (ImageView) viewHeader.findViewById(R.id.nav_header_profile_imageview);
        ImageView imageViewNavheaderLogout = (ImageView) viewHeader.findViewById(R.id.nav_header_logout_iv);
        navigationWelcome = viewHeader.findViewById(R.id.navigation_welcome);

        navigationWelcome.setText("Welcome, " + PreferenceUtils.getUserName(this));
        navigationWelcome.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));

        expandableListView = findViewById(R.id.expandableListView);
        prepareMenuData();
        populateExpandableList();

        profileName=findViewById(R.id.profile_name);
        logout_second=findViewById(R.id.logout_second);
        profile=findViewById(R.id.profile_second);
        offline=findViewById(R.id.offline);
        dots=findViewById(R.id.dots_report);

        drawer_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });
        dots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(UserMessages.this, dots);

                // Inflating popup menu from popup_menu.xml file
                popupMenu.getMenuInflater().inflate(R.menu.main, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        // Toast message on menu item clicked
                        switch (menuItem.getItemId())
                        {

                            case R.id.report:
                                Intent reportIntent = new Intent(UserMessages.this, ReportActivity.class);
                                startActivity(reportIntent);
                                return true;
                            case R.id.action_privacy:
                                Intent privacyIntent = new Intent(UserMessages.this, PrivacyPolicyActivity.class);
                                startActivity(privacyIntent);
                                return true;
                        }


                        return true;
                    }
                });
                // Showing the popup menu
                popupMenu.show();
            }
        });


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMessages.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        logout_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceUtils.savePassword(null, UserMessages.this);
                PreferenceUtils.saveEmail(null, UserMessages.this);
                PreferenceUtils.saveUid(null, UserMessages.this);
                // MyService.count = 0;

                //trying to stop the service
              /*  stopService = true;
                Intent stopIntent = new Intent(getApplicationContext(), MyService.class);
                stopIntent.putExtra("service", "yes");
                stopIntent.setAction("stopService");
                getApplicationContext().startService(stopIntent);*/
                final ProgressDialog progressDoalog;
                progressDoalog = new ProgressDialog(UserMessages.this);
                progressDoalog.setMessage("Logging Out....");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.show();

                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(i);
                finish();
            }
        });


        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMessages.this, ViewEmployeeActivity.class);
                startActivity(intent);
            }
        });


        profileName.setText(PreferenceUtils.getUserName(getApplicationContext()));
        tabLayoutMessages.setTabGravity(TabLayout.GRAVITY_FILL);

        final UserMessagesAdapter messagesAdapter = new UserMessagesAdapter(this, getSupportFragmentManager(), tabLayoutMessages.getTabCount());
        viewPagerMessages.setAdapter(messagesAdapter);

        viewPagerMessages.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayoutMessages));

        tabLayoutMessages.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPagerMessages.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



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
                        Toast.makeText(UserMessages.this, "Dashboard", Toast.LENGTH_SHORT).show();
                    }
                }
                if (groupPosition==4){
                    startActivity(new Intent(UserMessages.this, ResolutionCorrespondenceActivity.class));

                }

                if(!(LoginActivity.checkValue())){
                    if(groupPosition == 6){

                        Intent intent=new Intent(UserMessages.this, CommitteeManagement.class);
                        startActivity(intent);
                    }}
                else{
                    if(groupPosition == 2){

                        Intent intent=new Intent(UserMessages.this,CommitteeManagement.class);
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
                            Intent intent = new Intent(UserMessages.this, AdminMessages.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(UserMessages.this, UserMessages.class);
                            startActivity(intent);
                        }
                    }

                    if (groupPosition == 5) {
                        if (childPosition == 2) {
                            Intent intent = new Intent(UserMessages.this, ReadyToApprove.class);
                            startActivity(intent);
                        }
                    }

                    if (groupPosition == 5) {
                        if (childPosition == 0) {
                            startActivity(new Intent(UserMessages.this, AddNewMeetingActivity.class));

                        }
                    }

                    if (groupPosition == 5) {
                        if (childPosition == 1) {
                            startActivity(new Intent(UserMessages.this, ManageMeetingsActivity.class));

                        }
                    }
                    if (groupPosition == 5){
                        if (childPosition==3)
                            startActivity(new Intent(UserMessages.this, ToAttendMeetingActivity.class));

                    }

                    if (groupPosition == 1) {
                        if (childPosition == 0) {
                            startActivity(new Intent(UserMessages.this, IncomingCorrespondenceActivity.class));

                        }
                    }

                    if (groupPosition == 1) {
                        if (childPosition == 1) {
                            startActivity(new Intent(UserMessages.this, OutgoingCorrespondenceActivity.class));

                        }
                    }

                    if (groupPosition == 2) {
                        if (childPosition == 0) {
                            startActivity(new Intent(UserMessages.this, AssignedCorrespondenceTaskActivity.class));

                        }
                    }
                }else{

                    if (groupPosition == 1) {
                        if (childPosition == 0) {
                            startActivity(new Intent(UserMessages.this, ToAttendMeetingActivity.class));
                        }
                    }
                    if (groupPosition == 3) {
                        if (childPosition == 0) {
                            Intent intent = new Intent(UserMessages.this, AdminMessages.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(UserMessages.this, UserMessages.class);
                            startActivity(intent);
                        }
                    }


                }



                return false;
            }
        });
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


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
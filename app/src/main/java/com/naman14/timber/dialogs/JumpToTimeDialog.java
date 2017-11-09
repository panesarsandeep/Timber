package com.naman14.timber.dialogs;


import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.InputType;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.naman14.timber.MusicPlayer;
import com.naman14.timber.MusicService;

/**
 * Created by SANDEEP PANESAR on 09-11-2017.
 */

public class JumpToTimeDialog extends DialogFragment {

    //private static long songId = -1;
    private static long duration, elapsedTime = -1;
    long min_progress = 0;

    public static JumpToTimeDialog newInstance() {
        duration = MusicPlayer.duration();
        elapsedTime = MusicPlayer.position();
        return newInstance((long) -1);
    }

    public static JumpToTimeDialog newInstance(long currentAudioID) {
        JumpToTimeDialog dialog = new JumpToTimeDialog();
        Bundle bundle = new Bundle();
        bundle.putLong("currentAudioId", currentAudioID);
        dialog.setArguments(bundle);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //
        return new MaterialDialog.Builder(getActivity()).positiveText("Jump").negativeText("Cancel").title("Jump to time\n" + "Min : 0\nMax : " + String.valueOf(duration) + " milliseconds").input("Time in Milliseconds", String.valueOf(elapsedTime), new MaterialDialog.InputCallback() {
            @Override
            public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                // TODO Move slider to different position
                if ((long) Integer.valueOf(input.toString()) >= duration) {
                    Toast.makeText(getContext(), "Please enter value lesser than " + String.valueOf(duration), Toast.LENGTH_LONG).show();
                } else {
                    MusicPlayer.seek((long) Integer.valueOf(input.toString()));
                }
                if (!MusicPlayer.isPlaying()) {
                    MusicPlayer.playOrPause();
                }
            }
        }).inputType(InputType.TYPE_CLASS_NUMBER).build();
    }
}

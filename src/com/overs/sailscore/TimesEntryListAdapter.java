/*
 * This is a custom list adapter used to bind data to the EntriesSelectListActivity list view.
 */

package com.overs.sailscore;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
//import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

    public class TimesEntryListAdapter extends BaseAdapter {
	protected static ArrayList<TimesObj> combinedList;
	private int[] colors = new int[] { Color.parseColor("#F0F0F0"), Color.parseColor("#D2E4FC") }; 
	private Context mContext;
	private LayoutInflater mInflater;
	public TimesEntryListAdapter(Context context, ArrayList<TimesObj> listToDisplay) {
		combinedList = listToDisplay;
		mInflater = LayoutInflater.from(context);
		mContext = context;
	}

	@Override
	public int getCount() {
		return combinedList.size();
	}

	@Override
	public Object getItem(int position) {
		return combinedList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override

	public int getViewTypeCount() {                 

	    return getCount();
	}

	@Override
	public int getItemViewType(int position) {

	    return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		final ViewHolder holder;
		combinedList.get(pos).setCodePriority(false);
		// Add an on click listener to this method that will update the source data in the calling activity when items change
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.times_row, null);
			holder = new ViewHolder();
			holder.race_line = (TextView) convertView.findViewById(R.id.race_line);
			holder.race_number = (TextView) convertView.findViewById(R.id.race_number);
			holder.start_mins = (EditText) convertView.findViewById(R.id.start_mins);
			holder.start_secs = (EditText) convertView.findViewById(R.id.start_secs);
			holder.finish_mins = (EditText) convertView.findViewById(R.id.finish_mins);
			holder.finish_secs = (EditText) convertView.findViewById(R.id.finish_secs);
			holder.result_code_spinner = (Spinner) convertView.findViewById(R.id.result_code_spinner);
			holder.laps_sailed = (EditText) convertView.findViewById(R.id.laps_sailed);
			holder.total_laps = (EditText) convertView.findViewById(R.id.total_laps);
			holder.rdg_pos = (EditText) convertView.findViewById(R.id.rdg_pos);
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		            mContext, R.array.result_codes, R.layout.codes_spinner_item);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    holder.result_code_spinner.setAdapter(adapter);
			convertView.setTag(holder);	
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final OnItemSelectedListener spinnerListener = new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> av, View v, int spinPosition, long id) {
				combinedList.get(pos).setSpinPosition(spinPosition);
				combinedList.get(pos).setCodePriority(true);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		};
		
		final TextWatcher startMinsTextWatcher = new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				combinedList.get(pos).setsMins(s.toString());
				combinedList.get(pos).setCodePriority(false);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		};

		final TextWatcher startSecsTextWatcher = new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				combinedList.get(pos).setsSecs(s.toString());
				combinedList.get(pos).setCodePriority(false);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		};

		final TextWatcher finishMinsTextWatcher = new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				combinedList.get(pos).setfMins(s.toString());
				combinedList.get(pos).setCodePriority(false);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		};

		final TextWatcher finishSecsTextWatcher = new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				combinedList.get(pos).setfSecs(s.toString());
				combinedList.get(pos).setCodePriority(false);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		};

		final TextWatcher lapsSailedTextWatcher = new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				combinedList.get(pos).setLaps(s.toString());
				combinedList.get(pos).setCodePriority(false);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		};

		final TextWatcher totalLapsTextWatcher = new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				combinedList.get(pos).setTotalLaps(s.toString());
				combinedList.get(pos).setCodePriority(false);
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		};

		final TextWatcher rdgTextWatcher = new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				combinedList.get(pos).setRedressPosition(s.toString());
				combinedList.get(pos).setCodePriority(true);
				// Forcing RDG doesn't work because this watcher is called every time the
				// view is refreshed, even when text hasn't changed.
				//combinedList.get(pos).setSpinPosition(11); // Force RDG
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
		};
				
		
		
		holder.result_code_spinner.setOnItemSelectedListener(spinnerListener);
		
		holder.start_mins.addTextChangedListener(startMinsTextWatcher);
		holder.start_secs.addTextChangedListener(startSecsTextWatcher);
		holder.finish_mins.addTextChangedListener(finishMinsTextWatcher);
		holder.finish_secs.addTextChangedListener(finishSecsTextWatcher);
		holder.laps_sailed.addTextChangedListener(lapsSailedTextWatcher);
		holder.total_laps.addTextChangedListener(totalLapsTextWatcher);
		holder.rdg_pos.addTextChangedListener(rdgTextWatcher);

		holder.race_line.setText(R.string.race_line);
		holder.race_number.setText(combinedList.get(position).getRaceNumber());
		holder.start_mins.setText(combinedList.get(position).getsMins());
		holder.start_secs.setText(combinedList.get(position).getsSecs());
		holder.finish_mins.setText(combinedList.get(position).getfMins());
		holder.finish_secs.setText(combinedList.get(position).getfSecs());
		holder.laps_sailed.setText(combinedList.get(position).getLaps());
		holder.total_laps.setText(combinedList.get(position).getTotalLaps());
		holder.rdg_pos.setText(combinedList.get(position).getRedressPosition());
		holder.result_code_spinner.setSelection(combinedList.get(position).getSpinPosition());

		// Set alternating colour pattern
		int colorPos = position % colors.length;  
		convertView.setBackgroundColor(colors[colorPos]);
		if (pos == 0) {
			holder.finish_mins.requestFocus();
		}
		return convertView;
	}

	static class ViewHolder {
		TextView race_line;
		TextView race_number;
		EditText start_mins;
		EditText start_secs;
		EditText finish_mins;
		EditText finish_secs;
		EditText laps_sailed;
		EditText total_laps;
		Spinner result_code_spinner;
		EditText rdg_pos;
	}
}

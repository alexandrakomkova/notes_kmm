package com.example.kmm_app.presentation.note_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.kmm_app.domain.NoteDataSource
import com.example.kmm_app.domain.NoteValidation
import com.example.kmm_app.domain.model.Note
import com.example.kmm_app.domain.model.order.NoteOrder
import com.example.kmm_app.domain.model.order.OrderType
import com.example.kmm_app.domain.time.DateTimeUtil
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteListViewModel (
    private val noteDataSource: NoteDataSource
): ViewModel() {
		/*private val notes = (1..9).map {
				Note(
						id = it.toLong(),
						title = "title $it",
						content = "content $it",
						timestamp = DateTimeUtil.now()
				)
		}



     private val _state = MutableStateFlow(NoteListState(notes = notes))
     val state = _state.asStateFlow()*/

    /*private val _state = MutableStateFlow(NoteListState())
		val state = combine(
				_state, noteDataSource.getAllNotes(_state.value.noteOrder)
		) { state, notes ->
				state.copy(notes = notes)
		}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), NoteListState())*/

    private val _state = MutableStateFlow(NoteListState())
    val state = _state.asStateFlow()



    var newNote: Note? by mutableStateOf(null)
        private set

    private var getNotesJob: Job? = null

   init {
        getNotes(_state.value.noteOrder)
   }

    fun onEvent(event: NoteListEvent) {
        when(event) {
            NoteListEvent.DeleteNote -> {
                viewModelScope.launch {
                    _state.value.selectedNote?.id?.let { id ->
                        _state.update { it.copy(
                            isSelectedNoteSheetOpen = false
                        ) }
                        noteDataSource.deleteNote(id)
                        delay(300L) // Animation delay
                        _state.update { it.copy(
                            selectedNote = null
                        ) }
                    }
                }
            }

            NoteListEvent.DismissNote -> {
                viewModelScope.launch {
                    _state.update { it.copy(
                        isSelectedNoteSheetOpen = false,
                        isAddNoteSheetOpen = false,
                        noteTitleError = null,
                        noteContentError = null
                    ) }
                    delay(300L) // Animation delay
                    newNote = null
                    _state.update { it.copy(
                        selectedNote = null
                    ) }
                }
            }

            is NoteListEvent.EditNote -> {
                _state.update { it.copy(
                    selectedNote = null,
                    isAddNoteSheetOpen = true,
                    isSelectedNoteSheetOpen = false
                ) }
                newNote = event.note
            }

            NoteListEvent.OnAddNewNoteClick -> {
                _state.update { it.copy(
                    isAddNoteSheetOpen = true
                ) }
                newNote = Note(
                    id = null,
                    title = "",
                    content = "",
                    timestamp = DateTimeUtil.now()
                )
            }

            is NoteListEvent.OnNoteTitleChanged -> {
                newNote = newNote?.copy(
                    title = event.value
                )
            }
            is NoteListEvent.OnNoteContentChanged -> {
                newNote = newNote?.copy(
                    content = event.value
                )
            }
            NoteListEvent.SaveNote -> {
                newNote?.let { note ->
                    val result = NoteValidation.validateNote(note)
                    val errors = listOfNotNull(
                        result.noteTitleError,
                        result.noteContentError,
                    )

                    if(errors.isEmpty()) {
                        _state.update { it.copy(
                            isAddNoteSheetOpen = false,
                            noteTitleError = null,
                            noteContentError = null
                        ) }
                        viewModelScope.launch {
                            noteDataSource.insertNote(note)
                            delay(300L) // Animation delay
                            newNote = null
                        }
                    } else {
                        _state.update { it.copy(
                            noteTitleError = result.noteTitleError,
                            noteContentError = result.noteContentError,
                        ) }

                    }
                }
            }

            is NoteListEvent.SelectNote -> {
                _state.update { it.copy(
                    selectedNote = event.note,
                    isSelectedNoteSheetOpen = true
                ) }
            }

            is NoteListEvent.ToggleOrderSection -> {
                _state.update { it.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                ) }
            }

            is NoteListEvent.Order -> {
                if(state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    Napier.d(message = "return happened", tag = "NoteListEvent.Order")
                    return
                }

              // Napier.d(message = "${event.noteOrder} - ${event.noteOrder.orderType}", tag = "NoteListEvent.Order")
                getNotes(event.noteOrder)
            }
        }
    }

    private fun getNotes(noteOrder: NoteOrder) {
        getNotesJob?.cancel()
        getNotesJob =
            noteDataSource.getAllNotes(noteOrder).onEach { notes ->
                val sortedList: List<Note> = when(noteOrder.orderType) {
                    is OrderType.Ascending -> {
                        when(noteOrder) {
                            is NoteOrder.Title -> notes.sortedBy { it.title.lowercase() }
                            is NoteOrder.Date -> notes.sortedBy { it.timestamp }
                        }
                    }

                    is OrderType.Descending -> {
                        when(noteOrder) {
                            is NoteOrder.Title -> notes.sortedByDescending { it.title.lowercase() }
                            is NoteOrder.Date -> notes.sortedByDescending { it.timestamp }
                        }
                    }
                }
                Napier.d(message = "$sortedList", tag = "NoteListEvent.Order notes")
                _state.update { it.copy(
                    notes = sortedList,
                    noteOrder = noteOrder
                ) }

            }.launchIn(viewModelScope)
    }


}
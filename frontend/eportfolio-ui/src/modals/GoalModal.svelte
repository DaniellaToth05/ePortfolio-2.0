<script lang="ts">
    let {
        open = false,
        goal = null,
        onClose,
        onSubmit
    } = $props<{
        open: boolean;
        goal: {
            id?: number;
            name: string;
            target: number;
            current: number;
        } | null;
        onClose: () => void;
        onSubmit: (goal: {
            id?: number;
            name: string;
            target: number;
            current: number;
        }) => void;
    }>();

  
    let name = $state("");
    let target = $state(0);
    let current = $state(0);

    $effect(() => {
        if (goal) {
            name = goal.name;
            target = goal.target;
            current = goal.current;
        }
    });

  
    function submit(e: SubmitEvent) {
      e.preventDefault();
      if (!name.trim() || target <= 0) return;
  
      onSubmit({
        id: goal?.id,
        name,
        target,
        current
      });
      name = "";
      target = 0;
      current = 0;
    }
  </script>
  
  {#if open}
  <div class="modal-overlay">
    <div class="modal">
      <button class="close-btn" onclick={onClose}>×</button>
  
      <div class="modal-header">
        <h2 class="modal-title">
            {goal ? "Edit Investment Goal" : "Add Investment Goal"}
          </h2>          
      </div>
  
      <form onsubmit={submit}>
        <div class="form-group">
          <label class="form-label" for="goal-name">Goal Name</label>
          <input
            id="goal-name"
            class="form-input"
            bind:value={name}
            required
          />
        </div>
  
        <div class="form-group">
          <label class="form-label" for="goal-target">Target Amount</label>
          <div class="price-input-wrapper">
            <input
              id="goal-target"
              class="form-input price-input"
              type="number"
              bind:value={target}
              required
            />
          </div>
        </div>

        <div class="form-group">
            <label class="form-label" for="goal-current">
              Current Progress
            </label>
            <div class="price-input-wrapper">
              <input
                id="goal-current"
                class="form-input price-input"
                type="number"
                bind:value={current}
              />
            </div>
        </div>
          
  
        <div class="button-group">
          <button type="button" class="btn btn-secondary" onclick={onClose}>
            Cancel
          </button>
          <button type="submit" class="btn btn-primary">
            Save Goal
          </button>
        </div>
      </form>
    </div>
  </div>
  {/if}
  
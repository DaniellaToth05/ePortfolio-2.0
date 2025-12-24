<script lang="ts">
    import GoalModal from "../modals/GoalModal.svelte";
  
    interface Investment {
      symbol: string;
      price: number;
      quantity: number;
    }
  
    interface Goal {
      id?: number;
      name: string;
      target: number;
      current: number;
    }

  
    let { investments } = $props<{ investments: Investment[] }>();
  
    let goals = $state<Goal[]>([]);
    let modalOpen = $state(false);
    let editingGoal = $state<Goal | null>(null);

    function openEdit(goal: Goal) {
        editingGoal = goal;
        modalOpen = true;
    }

    async function loadGoals() {
      const res = await fetch("/api/goals");
      goals = await res.json();
    }
  
    async function saveGoal(goal: Goal) {
        const res = await fetch(
            goal.id ? `/api/goals/${goal.id}` : "/api/goals",
            {
                method: goal.id ? "PUT" : "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(goal)
            }
        );

        const saved = await res.json();

        if (goal.id) {
            goals = goals.map(g => (g.id === saved.id ? saved : g));
        } else {
            goals = [...goals, saved];
        }

        modalOpen = false;
        editingGoal = null;
    }

  

    const computedGoals = $derived(() =>
        goals.map(g => {
            const current = g.current ?? 0;
            const percent =
            g.target > 0 ? Math.min(100, (current / g.target) * 100) : 0;
            const remaining = Math.max(0, g.target - current);

            return {
            ...g,
            current,
            percent,
            remaining
            };
        })
    );
    loadGoals();
  </script>
  
  <section class="goals-section">
    <div class="floating-element leaf-1"></div>
    <div class="floating-element leaf-2"></div>
    <div class="floating-element sage-accent"></div>
  
    <h2 class="goals-title">Investment Objectives</h2>
  
    <div class="goals-grid">
      {#if computedGoals().length === 0}
        <div class="goal-item">
          <div class="progress-text">
            No goals yet — add one to start tracking.
          </div>
        </div>
      {:else}
        {#each computedGoals() as goal}
        <div class="goal-item">
            <div style="display: flex; justify-content: space-between; align-items: center;">
              <div class="goal-name">{goal.name}</div>
          
              <button
                class="goal-edit"
                type="button"
                onclick={() => openEdit(goal)}
                >
                Edit
            </button>

            </div>
          
  
            <div class="progress-container">
              <div class="progress-bar">
                <div
                  class="progress-fill"
                  style="width: {goal.percent}%"
                ></div>
              </div>
  
              <div class="progress-text">
                {Math.round(goal.percent)}% Complete ·
                ${goal.current.toLocaleString()} of
                ${goal.target.toLocaleString()}
              </div>
  
              <div class="progress-text">
                ${goal.remaining.toLocaleString()} remaining
              </div>
            </div>
          </div>
        {/each}
      {/if}
    </div>
  
    <div style="text-align:center; margin-top: 40px;">
      <button class="btn btn-primary" onclick={() => modalOpen = true}>
        Add Goal
      </button>
    </div>
  </section>
  
  
  <GoalModal
    open={modalOpen}
    goal={editingGoal}
    onClose={() => {
        modalOpen = false;
        editingGoal = null;
    }}
    onSubmit={saveGoal}
  />
